package cn.p2nn.meteor.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 配置
 *
 * @author huangjiayao1993
 */
@Data
@TableName
@Accessors(chain = true)
public class SysConfig implements Serializable {

    @TableId
    private String id;

    /**
     * 字典类型
     */
    @NotBlank
    @TableField("`key`")
    private String key;

    /**
     * 字典数据值
     */
    @NotBlank
    @TableField("`value`")
    private String value;

}
