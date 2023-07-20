package cn.p2nn.meteor.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * oss云存储配置
 *
 * @author huangjiayao1993
 */
@Data
@Component
@ConfigurationProperties(prefix = "meteor.oss")
public class OssConfig {

    private String type;

    private String domain;

    private String bucket;

}
