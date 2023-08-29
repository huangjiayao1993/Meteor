package cn.p2nn.meteor.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

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

    /**
     * RSA私钥
     */
    private String rsaPrivate;

    /**
     * RSA公钥
     */
    private String rsaPublic;

    /**
     * AES私钥
     */
    private String aesPrivate;
}
