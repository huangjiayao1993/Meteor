package cn.p2nn.meteor.config.oss;

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

    private OssHuaweiConfig huaweiConfig;

    private OssAliConfig aliConfig;

    private OssTencentConfig tencentConfig;

    private OssQiniuConfig qiniuConfig;

    private OssMinioConfig minioConfig;

}
