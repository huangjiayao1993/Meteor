package cn.p2nn.meteor.service;

import cn.p2nn.meteor.entity.SysOrg;
import cn.p2nn.meteor.mapper.SysOrgMapper;
import cn.p2nn.meteor.model.PageResult;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SysOrgService extends ServiceImpl<SysOrgMapper, SysOrg> {

    public PageResult page(Page page, SysOrg org) {
        page = this.page(page, Wrappers.lambdaQuery(SysOrg.class).like(StringUtils.isNotBlank(org.getName()), SysOrg::getName, org.getName()).eq(StringUtils.isNotBlank(org.getId()), SysOrg::getPid, org.getId()));
        return PageResult.parse(page);
    }

    public List<SysOrg> tree(SysOrg org) {
        List<SysOrg> list = this.getTree(org.getId());
        return list;
    }

    List<SysOrg> getTree(String pid) {
        LambdaQueryWrapper<SysOrg> qw = Wrappers.lambdaQuery();
        if (StringUtils.isEmpty(pid)) {
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
}
