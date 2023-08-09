package cn.p2nn.meteor.service.oss;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.Singleton;
import cn.hutool.core.util.EnumUtil;
import cn.hutool.extra.spring.SpringUtil;
import cn.p2nn.meteor.config.oss.OssConfig;
import cn.p2nn.meteor.constants.CacheConstant;
import cn.p2nn.meteor.enums.ResultEnum;
import cn.p2nn.meteor.enums.ThirdEnum;
import cn.p2nn.meteor.exception.OssException;
import cn.p2nn.meteor.service.RedisService;
import org.apache.commons.lang3.ObjectUtils;
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
        if (ObjectUtils.isEmpty(redisService)) {
            initRedis();
        }
        if (ObjectUtils.isEmpty(config)) {
            initConfig();
        }
        Oss oss;
        String type = redisService.get(CacheConstant.OSS_TYPE);
        if (StringUtils.isBlank(type)) {
            type = config.getType();
        }
        ThirdEnum third = EnumUtil.fromStringQuietly(ThirdEnum.class, StringUtils.upperCase(type));
        Assert.notNull(third, () -> {
            throw new OssException(ResultEnum.MISSING_OSS_DEFAULT_ERROR);
        });
        switch (third) {
            case HUAWEI:
                oss = getHuawei();
                break;
            case ALI:
                oss = getAli();
                break;
            case TENCENT:
                oss = getTencent();
                break;
            case QINIU:
                oss = getQiniu();
                break;
            case MINIO:
                oss = getMinio();
                break;
            default:
                throw new OssException(ResultEnum.MISSING_OSS_DEFAULT_ERROR);
        }
        return oss;
    }

    public static OssHuawei getHuawei() {
        if (ObjectUtils.isEmpty(huawei)) {
            huawei = Singleton.get(OssHuawei.class);
        }
        return huawei;
    }

    public static OssAli getAli() {
        if (ObjectUtils.isEmpty(ali)) {
            ali = Singleton.get(OssAli.class);
        }
        return ali;
    }

    public static OssTencent getTencent() {
        if (ObjectUtils.isEmpty(tencent)) {
            tencent = Singleton.get(OssTencent.class);
        }
        return tencent;
    }

    public static OssQiniu getQiniu() {
        if (ObjectUtils.isEmpty(qiniu)) {
            qiniu = Singleton.get(OssQiniu.class);
        }
        return qiniu;
    }

    public static OssMinio getMinio() {
        if (ObjectUtils.isEmpty(minio)) {
            minio = Singleton.get(OssMinio.class);
        }
        return minio;
    }
}
