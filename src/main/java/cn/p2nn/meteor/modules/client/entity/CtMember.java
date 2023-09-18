package cn.p2nn.meteor.modules.client.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 客户端会员
 *
 * @author huangjiayao1993
 */
@Data
@TableName
@Accessors(chain = true)
public class CtMember implements Serializable {

    @Serial
    private static final long serialVersionUID = 1549380597346494615L;

    /**
     * 主键
     */
    @TableId
    protected String id;

    /**
     * 微信openid
     */
    @TableField(exist = false)
    private String wxOpenid;

    /**
     * 账号ID
     */
    private String accountId;

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

}
