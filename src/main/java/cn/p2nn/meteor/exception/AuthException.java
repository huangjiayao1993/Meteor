package cn.p2nn.meteor.exception;

import cn.hutool.core.util.StrUtil;
import cn.p2nn.meteor.enums.ResultEnum;
import lombok.Data;

/**
 * 认证相关异常
 *
 * @author huangjiayao1993
 */
@Data
public class AuthException extends RuntimeException {

    private int code;

    private String msg;

    public AuthException(ResultEnum r) {
        super(r.getMsg());
        this.code = r.getCode();
        this.msg = r.getMsg();
    }

    public AuthException(ResultEnum r, String extMsg) {
        super(StrUtil.join(r.getMsg(), extMsg));
        this.code = r.getCode();
        this.msg = StrUtil.join(r.getMsg(), extMsg);
    }

}
