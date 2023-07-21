package cn.p2nn.meteor.entity;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 组织
 *
 * @author huangjiayao1993
 */
@Data
@TableName
@Accessors(chain = true)
public class SysOrg implements Serializable {

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
     * 组织名称
     */
    @TableField("`name`")
    private String name;

    @TableField(exist = false)
    private String title;

    @TableField(exist = false)
    private String label;

    /**
     * 组织类型,0:部门，1:公司
     */
    @TableField("`type`")
    private Integer type;

    /**
     * 排序
     */
    private Integer sort;

    @TableField(exist = false)
    private List<SysOrg> children;
}
