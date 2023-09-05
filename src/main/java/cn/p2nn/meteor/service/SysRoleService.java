package cn.p2nn.meteor.service;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import cn.p2nn.meteor.entity.SysRole;
import cn.p2nn.meteor.entity.SysUserRole;
import cn.p2nn.meteor.enums.ResultEnum;
import cn.p2nn.meteor.exception.BusinessException;
import cn.p2nn.meteor.mapper.SysRoleMapper;
import cn.p2nn.meteor.model.PageResult;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SysRoleService extends ServiceImpl<SysRoleMapper, SysRole> {

    private final SysUserRoleService userRoleService;

    public PageResult page(Page page, SysRole role) {
        LambdaQueryWrapper<SysRole> qw = Wrappers.lambdaQuery(SysRole.class).like(StrUtil.isNotBlank(role.getName()), SysRole::getName, role.getName());
        page = this.page(page, qw);
        return PageResult.parse(page);
    }

    public List<SysRole> list(SysRole role) {
        LambdaQueryWrapper<SysRole> qw = Wrappers.lambdaQuery(SysRole.class).like(StrUtil.isNotBlank(role.getName()), SysRole::getName, role.getName());
        List<SysRole> list = this.list(qw);
        return list;
    }

    public List<String> listRoleCode(String userId) {
        List<SysRole> roleList = this.userRoleService.listByUserId(userId);
        List<String> codeList = roleList.stream().map(SysRole::getCode).collect(Collectors.toList());
        return codeList;
    }

    @Transactional(rollbackFor = Exception.class)
    public void create(SysRole role) {
        long count = this.count(Wrappers.lambdaQuery(SysRole.class).eq(SysRole::getCode, role.getCode()));
        Assert.isFalse(count > 0, () -> {throw new BusinessException(ResultEnum.ROLE_CODE_EXISTS);});
        this.save(role);
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(SysRole role) {
        long count = this.count(Wrappers.lambdaQuery(SysRole.class).eq(SysRole::getCode, role.getCode()).notIn(SysRole::getId, role.getId()));
        Assert.isFalse(count > 0, () -> {throw new BusinessException(ResultEnum.ROLE_CODE_EXISTS);});
        this.updateById(role);
    }

    @Transactional(rollbackFor = Exception.class)
    public void remove(List<String> ids) {
        long count = this.userRoleService.count(Wrappers.lambdaQuery(SysUserRole.class).in(SysUserRole::getRoleId, ids));
        Assert.isFalse(count > 0, () -> {throw new BusinessException(ResultEnum.ROLE_ASSIGNED_TO_USER);});
        this.removeBatchByIds(ids);
    }
}
