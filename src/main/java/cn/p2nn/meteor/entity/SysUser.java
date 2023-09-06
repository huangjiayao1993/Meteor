package cn.p2nn.meteor.entity;

import java.io.Serial;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.fasterxml.jackson.annotation.JsonIgnore;

import cn.p2nn.meteor.constants.CommonConstant;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用户
 *
 * @author huangjiayao1993
 */
@Data
@TableName
@Accessors(chain = true)
public class SysUser extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 3655267182923201138L;

    /**
     * 用户名
     */
    @TableField(exist = false)
    private String username;

    /**
     * 密码
     */
    @TableField(exist = false)
    private String password;

    /**
     * 新密码
     */
    @TableField(exist = false)
    private String newPassword;

    /**
     * 盐
     */
    @TableField(exist = false)
    private String salt;

    /**
     * 账号ID
     */
    private String accountId;

    /**
     * 组织ID
     */
    private String orgId;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 真实姓名
     */
    private String realname;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 性别,是否男，true/1:男，false/0:女
     */
    private boolean gender;

    /**
     * 状态,是否停用，true/1:停用，false/0:正常
     */
    @TableField("`status`")
    private boolean status;

    /**
     * 删除标记,是否已删除，true/1:已删除，false/0:未删除
     */
    @JsonIgnore
    private boolean deleted;

    /**
     * 注册时间
     */
    private LocalDateTime regTime;

    /**
     * 账号使用期限开关,是否启用，true/1:启用，false/0:不启用
     */
    private boolean enableUsed;

    /**
     * 账号使用期限-起始时间
     */
    private LocalDateTime enableStartTime;

    /**
     * 账号使用期限-结束时间
     */
    private LocalDateTime enableEndTime;

    @Version
    private long version;

    /**
     * 最后登录时间
     */
    private LocalDateTime loginTime;

    public boolean isAdmin() {
        return CommonConstant.ADMIN_ID.equals(this.getId());
    }
}
