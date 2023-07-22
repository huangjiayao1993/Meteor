package cn.p2nn.meteor.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 登录日志
 *
 * @author huangjiayao1993
 */
@Data
@TableName
@Accessors(chain = true)
public class SysLoginLog implements Serializable {

    @TableId
    private String id;

    /**
     * 账号
     */
    private String username;

    /**
     * 设备
     */
    private String device;

    /**
     * 登录方式
     */
    private String loginType;

    /**
     * ip地址
     */
    private String ip;

    /**
     * 请求时间
     */
    private LocalDateTime createTime;

    /**
     * 请求是否成功,false/0:失败，true/1:成功
     */
    private boolean success;

    /**
     * 响应数据json
     */
    private String responseJson;

    /**
     * 异常原因
     */
    private String reason;
}
