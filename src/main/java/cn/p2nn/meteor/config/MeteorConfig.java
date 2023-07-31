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

    private String defaultPassword;

    private int captchaTimeout = 5;

}
