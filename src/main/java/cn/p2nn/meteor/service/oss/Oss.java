package cn.p2nn.meteor.service.oss;

import java.io.InputStream;

public abstract class Oss {

    protected static String TOKEN;

    protected static String BUCKET;

    public abstract void get();

    public abstract String upload(InputStream is, String objectKey);

    public abstract void remove();

}
