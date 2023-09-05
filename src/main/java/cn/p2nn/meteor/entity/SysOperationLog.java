package cn.p2nn.meteor.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 操作日志
 *
 * @author huangjiayao1993
 */
@Data
@TableName
@Accessors(chain = true)
public class SysOperationLog implements Serializable {

    @TableId
    private String id;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 账号
     */
    private String username;

    /**
     * ip地址
     */
    private String ip;

    /**
     * 请求URL
     */
    private String url;

    /**
     * 控制器
     */
    private String controller;

    /**
     * 方法
     */
    private String method;

    /**
     * 请求数据json
     */
    private String paramsJson;

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
     * 时长,单位秒
     */
    private long duration;

    /**
     * 异常原因
     */
    private String reason;

}
