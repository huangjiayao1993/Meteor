package cn.p2nn.meteor.service.oss;

import cn.hutool.extra.spring.SpringUtil;
import cn.p2nn.meteor.config.oss.OssHuaweiConfig;
import com.obs.services.ObsClient;
import com.obs.services.model.PutObjectResult;

import java.io.InputStream;

public class OssHuawei extends Oss {

    /**
     * 保证内存可见性，防止编译器过度优化(指令重排序)
     */
    private static volatile ObsClient client = null;

    private static ObsClient getClient() {
        if(client == null) {
            synchronized (OssHuawei.class) {
                if(client == null) {
                    OssHuaweiConfig bean = SpringUtil.getBean(OssHuaweiConfig.class);
                    client = new ObsClient(bean.getAk(), bean.getSk(), bean.getEndpoint());
                    BUCKET = bean.getBucket();
                }
            }
        }
        return client;
    }

    @Override
    public void get() {

    }

    @Override
    public String upload(InputStream is, String objectKey) {
        PutObjectResult result = getClient().putObject(BUCKET, objectKey, is);
        return result.getObjectUrl();
    }

    @Override
    public void remove() {

    }
}
