package cn.p2nn.meteor.entity;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 菜单
 *
 * @author huangjiayao1993
 */
@Data
@TableName
@Accessors(chain = true)
public class SysMenu implements Serializable {

    @TableId
    private String id;

    @TableField(exist = false)
    private String key;

    @TableField(exist = false)
    private String value;

    /**
     * 上级id
     */
    private String pid;

    /**
     * 菜单名称
     */
    @TableField("`name`")
    private String name;

    @TableField(exist = false)
    private String title;

    @TableField(exist = false)
    private String label;

    /**
     * 菜单类型
     */
    @TableField("`type`")
    private Integer type;

    /**
     * 权限标识
     */
    private String permission;

    /**
     * 访问地址
     */
    private String path;

    /**
     * 组件地址
     */
    private String componentPath;

    /**
     * 图标
     */
    private String icon;

    /**
     * 排序
     */
    private Integer sort;

    @TableField(exist = false)
    private List<SysMenu> children;
}
