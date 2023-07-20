package cn.p2nn.meteor.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.p2nn.meteor.entity.SysMenu;
import cn.p2nn.meteor.entity.SysRoleMenu;
import cn.p2nn.meteor.mapper.SysMenuMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SysMenuService extends ServiceImpl<SysMenuMapper, SysMenu> {

    private final SysRoleMenuService roleMenuService;

    public List<SysMenu> currentUserMenu(SysMenu menu) {
        List<String> inIds = this.baseMapper.listMenu(StpUtil.getLoginId().toString()).stream().map(SysMenu::getId).collect(Collectors.toList());
        if (inIds.isEmpty()) {
            return new ArrayList<>();
        }
        List<SysMenu> list = this.getTree(inIds, new Integer[]{2}, menu.getId());
        return list;
    }

    public List<SysMenu> tree(SysMenu menu) {
        List<SysMenu> list = this.getTree(new ArrayList<>(), null, menu.getId());
        return list;
    }

    public List<String> listPermissionCode(String userId) {
        List<SysMenu> menuList = this.baseMapper.listMenu(userId);
        List<String> permissionList = menuList.stream().map(SysMenu::getPermission).collect(Collectors.toList());
        return permissionList;
    }

    @Transactional(rollbackFor = Exception.class)
    public void remove(List<String> ids) {
        this.roleMenuService.remove(Wrappers.lambdaQuery(SysRoleMenu.class).in(SysRoleMenu::getMenuId, ids));
        this.removeBatchByIds(ids);
    }

    public List<SysMenu> getTree(List<String> inIds, Integer[] excludeTypes, String pid) {
        LambdaQueryWrapper<SysMenu> qw = Wrappers.lambdaQuery();
        if (StringUtils.isEmpty(pid)) {
            qw.isNull(SysMenu::getPid);
        } else {
            qw.eq(SysMenu::getPid, pid);
        }
        qw.in(!inIds.isEmpty(), SysMenu::getId, inIds);
        qw.notIn(ObjectUtil.isNotNull(excludeTypes), SysMenu::getType, excludeTypes);
        //查询条件
        List<SysMenu> list = this.list(qw);
        for (SysMenu item : list) {
            //递归子类数据
            List<SysMenu> children = this.getTree(inIds, excludeTypes, item.getId());
            if (children.size() < 1) {
                children = null;
            }
            item.setValue(item.getId()).setKey(item.getId()).setLabel(item.getName()).setTitle(item.getName()).setChildren(children);
        }
        return list;
    }

}
