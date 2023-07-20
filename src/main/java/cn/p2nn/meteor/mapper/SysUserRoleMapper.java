package cn.p2nn.meteor.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import cn.p2nn.meteor.entity.SysRole;
import cn.p2nn.meteor.entity.SysUserRole;

@Mapper
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

    List<SysRole> listByUserId(@Param("userId") String userId);

}
