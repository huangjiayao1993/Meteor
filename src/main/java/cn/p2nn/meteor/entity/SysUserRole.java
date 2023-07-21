package cn.p2nn.meteor.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用户角色
 *
 * @author huangjiayao1993
 */
@Data
@TableName
@Accessors(chain = true)
public class SysUserRole implements Serializable {

    private String id;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 角色ID
     */
    private String roleId;

}