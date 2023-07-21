package cn.p2nn.meteor.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 角色菜单
 *
 * @author huangjiayao1993
 */
@Data
@TableName
@Accessors(chain = true)
public class SysRoleMenu implements Serializable {

    @TableId
    private String id;

    /**
     * 角色ID
     */
    private String roleId;

    /**
     * 菜单ID
     */
    private String menuId;
}
