package cn.p2nn.meteor.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.hutool.core.util.ObjectUtil;
import cn.p2nn.meteor.dto.AuthorizeDto;
import cn.p2nn.meteor.entity.SysMenu;
import cn.p2nn.meteor.entity.SysRoleMenu;
import cn.p2nn.meteor.mapper.SysRoleMenuMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SysRoleMenuService extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> {

    public List<SysMenu> listByRoleId(String roleId) {
        return this.baseMapper.listByRoleId(roleId);
    }

    @Transactional(rollbackFor = Exception.class)
    public void authorize(AuthorizeDto dto) {
        this.remove(Wrappers.lambdaQuery(SysRoleMenu.class).eq(SysRoleMenu::getRoleId, dto.getId()));
        if (ObjectUtil.isNotEmpty(dto.getAuthIds())) {
            List<SysRoleMenu> list = new ArrayList<>();
            dto.getAuthIds().stream().forEach(menuId -> {
                SysRoleMenu data = new SysRoleMenu();
                data.setRoleId(dto.getId()).setMenuId(menuId);
                list.add(data);
            });
            this.saveBatch(list);
        }
    }

}
