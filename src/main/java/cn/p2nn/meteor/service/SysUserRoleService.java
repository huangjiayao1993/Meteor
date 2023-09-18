package cn.p2nn.meteor.service;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.p2nn.meteor.dto.AuthorizeDto;
import cn.p2nn.meteor.entity.SysRole;
import cn.p2nn.meteor.entity.SysUserRole;
import cn.p2nn.meteor.mapper.SysUserRoleMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SysUserRoleService extends ServiceImpl<SysUserRoleMapper, SysUserRole> {

    public List<SysRole> listByUserId(String userId) {
        return this.baseMapper.listByUserId(userId);
    }

    @Transactional(rollbackFor = Exception.class)
    public void authorize(AuthorizeDto dto) {
        this.remove(Wrappers.lambdaQuery(SysUserRole.class).eq(SysUserRole::getUserId, dto.getId()));
        if (ObjectUtil.isNotEmpty(dto.getAuthIds())) {
            List<SysUserRole> list = ListUtil.empty();
            dto.getAuthIds().forEach(roleId -> {
                SysUserRole data = new SysUserRole();
                data.setUserId(dto.getId()).setRoleId(roleId);
                list.add(data);
            });
            this.saveBatch(list);
        }
    }

}
