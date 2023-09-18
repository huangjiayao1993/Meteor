package cn.p2nn.meteor.exception;

import cn.p2nn.meteor.enums.ResultEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;

import java.io.Serial;

/**
 * 云存储相关异常
 *
 * @author huangjiayao1993
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class OssException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 5722422485494191840L;

    private int code;

    private String msg;

    public OssException(ResultEnum r) {
        super(r.getMsg());
        this.code = r.getCode();
        this.msg = r.getMsg();
    }

    public OssException(ResultEnum r, String extMsg) {
        super(StringUtils.join(r.getMsg(), extMsg));
        this.code = r.getCode();
        this.msg = StringUtils.join(r.getMsg(), extMsg);
    }

}
