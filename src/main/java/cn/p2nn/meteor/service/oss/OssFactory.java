package cn.p2nn.meteor.service.oss;

import cn.hutool.core.lang.Singleton;
import cn.hutool.core.util.EnumUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import cn.p2nn.meteor.config.OssConfig;
import cn.p2nn.meteor.constants.CacheConstant;
import cn.p2nn.meteor.enums.ResultEnum;
import cn.p2nn.meteor.enums.ThirdEnum;
import cn.p2nn.meteor.exception.OssException;
import cn.p2nn.meteor.service.RedisService;
import org.apache.commons.lang3.StringUtils;

/**
 * OSS云存储工厂
 *
 * @author huangjiayao1993
 */
public class OssFactory {

    private static OssConfig config;

    private static RedisService redisService;

    private static OssHuawei huawei;

    private static OssAli ali;

    private static OssTencent tencent;

    private static OssQiniu qiniu;

    private static OssMinio minio;

    static {
        initRedis();
        initConfig();
    }

    private static void initRedis() {
        redisService = SpringUtil.getBean(RedisService.class);
    }

    private static void initConfig() {
        config = SpringUtil.getBean(OssConfig.class);
    }

    public static Oss build() {
        if (ObjectUtil.isEmpty(redisService)) {
            initRedis();
        }
        if (ObjectUtil.isEmpty(config)) {
            initConfig();
        }
        Oss oss;
        String type = redisService.get(CacheConstant.OSS_TYPE);
        if (StrUtil.isBlank(type)) {
            type = config.getType();
        }
        ThirdEnum third = EnumUtil.fromStringQuietly(ThirdEnum.class, StringUtils.upperCase(type));
        switch (third) {
            case HUAWEI -> oss = getHuawei();
            case ALI -> oss = getAli();
            case TENCENT -> oss = getTencent();
            case QINIU -> oss = getQiniu();
            case MINIO -> oss = getMinio();
            default -> throw new OssException(ResultEnum.MISSING_OSS_DEFAULT_ERROR);
        }
        return oss;
    }

    public static OssHuawei getHuawei() {
        if (ObjectUtil.isEmpty(huawei)) {
            huawei = Singleton.get(OssHuawei.class);
        }
        return huawei;
    }

    public static OssAli getAli() {
        if (ObjectUtil.isEmpty(ali)) {
            ali = Singleton.get(OssAli.class);
        }
        return ali;
    }

    public static OssTencent getTencent() {
        if (ObjectUtil.isEmpty(tencent)) {
            tencent = Singleton.get(OssTencent.class);
        }
        return tencent;
    }

    public static OssQiniu getQiniu() {
        if (ObjectUtil.isEmpty(qiniu)) {
            qiniu = Singleton.get(OssQiniu.class);
        }
        return qiniu;
    }

    public static OssMinio getMinio() {
        if (ObjectUtil.isEmpty(minio)) {
            minio = Singleton.get(OssMinio.class);
        }
        return minio;
    }
}
