package cn.p2nn.meteor.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * 自定义配置
 *
 * @author huangjiayao1993
 */
@Data
@Component
@ConfigurationProperties(prefix = "meteor")
public class MeteorConfig {

    /**
     * 默认密码
     */
    private String defaultPassword;

    /**
     * 验证码过期时长，单位：分钟
     */
    private int captchaTimeout = 5;

}
