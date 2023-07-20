package cn.p2nn.meteor.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 字典
 *
 * @author huangjiayao1993
 */
@Data
@TableName
@Accessors(chain = true)
public class SysDict implements Serializable {

    @TableId
    private String id;

    /**
     * 字典名称
     */
    @TableField("`name`")
    private String name;

    /**
     * 字典类型
     */
    @TableField("`type`")
    private String type;

}
