package cn.p2nn.meteor.model;

import cn.p2nn.meteor.enums.ResultEnum;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Result<T> {

    private int code;

    private String msg;

    private T data;

    private boolean success;

    public static <T> Result success() {
        return new Result<T>(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg(), null);
    }

    public static <T> Result success(T data) {
        return new Result<T>(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg(), data);
    }

    public static <T> Result failed() {
        return new Result<T>(ResultEnum.FAIL.getCode(), ResultEnum.FAIL.getMsg(), null);
    }

    public static <T> Result failed(String msg) {
        return new Result<T>(ResultEnum.FAIL.getCode(), msg, null);
    }

    public static <T> Result failed(ResultEnum e) {
        return new Result<>(e.getCode(), e.getMsg(), null);
    }

    public static <T> Result failed(int code, String msg) {
        return new Result<>(code, msg, null);
    }

    private Result() {}

    private Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.success = ResultEnum.SUCCESS.getCode() == code;
    }
}
