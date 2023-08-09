package cn.p2nn.meteor.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 第三方标识枚举
 *
 * @author huangjiayao1993
 */
@Getter
@AllArgsConstructor
public enum ThirdEnum {

    HUAWEI(0, "huawei", "华为"),
    ALI(1, "ali", "阿里"),
    TENCENT(2, "tencent", "腾讯"),
    QINIU(3, "qiniu", "七牛"),
    MINIO(4, "minio", "minio"),
    ;

    private int code;

    private String type;

    private String desc;

}
