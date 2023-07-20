package cn.p2nn.meteor.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import cn.p2nn.meteor.entity.SysMenu;
import cn.p2nn.meteor.entity.SysRoleMenu;

@Mapper
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {

    List<SysMenu> listByRoleId(@Param("roleId") String roleId);

}
