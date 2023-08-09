package cn.p2nn.meteor.utils;

import cn.p2nn.meteor.service.oss.OssFactory;

import java.io.InputStream;

/**
 * OSS云存储工具
 *
 * @author huangjiayao1993
 */
public class OssUtil {

    public static void get() {
        OssFactory.build().get();
    }

    public static String upload(InputStream is, String objectKey) {
        return OssFactory.build().upload(is, objectKey);
    }

    public static void remove() {
        OssFactory.build().remove();
    }

}
