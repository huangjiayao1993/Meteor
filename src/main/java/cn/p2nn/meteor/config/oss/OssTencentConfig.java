package cn.p2nn.meteor.config.oss;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "meteor.oss.tencent")
public class OssTencentConfig {

    private String endpoint;

    private String bucket;

    private String ak;

    private String sk;

}
