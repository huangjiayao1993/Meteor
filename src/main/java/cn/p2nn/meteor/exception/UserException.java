package cn.p2nn.meteor.exception;

import org.apache.commons.lang3.StringUtils;

import cn.p2nn.meteor.enums.ResultEnum;
import lombok.Data;

/**
 * 用户相关异常
 *
 * @author huangjiayao1993
 */
@Data
public class UserException extends RuntimeException {

    private int code;

    private String msg;

    public UserException(ResultEnum r) {
        super(r.getMsg());
        this.code = r.getCode();
        this.msg = r.getMsg();
    }

    public UserException(ResultEnum r, String extMsg) {
        super(StringUtils.join(r.getMsg(), extMsg));
        this.code = r.getCode();
        this.msg = StringUtils.join(r.getMsg(), extMsg);
    }

}
