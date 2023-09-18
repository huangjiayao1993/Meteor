package cn.p2nn.meteor.exception;

import cn.p2nn.meteor.enums.ResultEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;

import java.io.Serial;

/**
 * 用户相关异常
 *
 * @author huangjiayao1993
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -2073683842528369312L;

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
