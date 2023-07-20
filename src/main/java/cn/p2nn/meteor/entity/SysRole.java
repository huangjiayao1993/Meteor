package cn.p2nn.meteor.entity;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import cn.p2nn.meteor.constants.CommonConstant;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 角色
 *
 * @author huangjiayao1993
 */
@Data
@TableName
@Accessors(chain = true)
public class SysRole implements Serializable {

    @TableId
    private String id;

    /**
     * 角色名称
     */
    @TableField("`name`")
    private String name;

    /**
     * 角色标识
     */
    @TableField("`code`")
    private String code;

    public boolean isAdmin() {
        return StringUtils.equalsIgnoreCase(CommonConstant.ADMIN_ROLE_ID, this.getId());
    }
}
