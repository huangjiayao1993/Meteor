package cn.p2nn.meteor.service;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import cn.p2nn.meteor.entity.SysOrg;
import cn.p2nn.meteor.entity.SysUser;
import cn.p2nn.meteor.enums.ResultEnum;
import cn.p2nn.meteor.exception.BusinessException;
import cn.p2nn.meteor.mapper.SysOrgMapper;
import cn.p2nn.meteor.model.PageResult;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SysOrgService extends ServiceImpl<SysOrgMapper, SysOrg> {

    private final SysUserService userService;

    public PageResult page(Page page, SysOrg org) {
        page = this.lambdaQuery()
                .like(StrUtil.isNotBlank(org.getName()), SysOrg::getName, org.getName())
                .eq(StrUtil.isNotBlank(org.getId()), SysOrg::getPid, org.getId())
                .page(page);
        return PageResult.parse(page);
    }

    public List<SysOrg> tree(SysOrg org) {
        return this.getTree(org.getId());
    }

    List<SysOrg> getTree(String pid) {
        LambdaQueryWrapper<SysOrg> qw = Wrappers.lambdaQuery();
        if (StrUtil.isEmpty(pid)) {
            qw.isNull(SysOrg::getPid);
        } else {
            qw.eq(SysOrg::getPid, pid);
        }
        //查询条件
        List<SysOrg> list = this.list(qw);
        for (SysOrg item : list) {
            List<SysOrg> children = this.getTree(item.getId());
            //递归子类数据
            if (children.isEmpty()) {
                children = null;
            }
            item.setValue(item.getId()).setKey(item.getId()).setLabel(item.getName()).setTitle(item.getName()).setChildren(children);
        }
        return list;
    }

    @Transactional(rollbackFor = Exception.class)
    public void remove(List<String> ids) {
        long count = this.userService.lambdaQuery().in(SysUser::getOrgId, ids).count();
        Assert.isFalse(count > 0, () -> {throw new BusinessException(ResultEnum.ORG_USER_EXISTS);});
        this.removeBatchByIds(ids);
    }
}
