package cn.p2nn.meteor.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultEnum {

    SUCCESS(0, "成功"),
    FAILED(-100000, "失败"),
    AUTH_FAILED(-100001, "Token认证异常"),

    USER_NOT_FOUND(-200000, "用户不存在"),
    USERNAME_PASSWORD_ERROR(-200001, "用户名或密码错误"),
    USER_DEACTIVATED(-200002, "用户已停用"),
    USER_NOTIN_USE_TIME(-200003, "用户不在使用期限内"),
    LOGIN_ERROR(-200004, "登录异常"),
    PERMISSION_ERROR(-200005, "权限异常"),
    ROLE_ERROR(-200006, "角色异常"),
    USERNAME_EXISTS(-200007, "用户名已存在"),
    OLD_PASSWORD_ERROR(-200008, "原密码错误"),
    NOT_REMOVE_ADMIN(-200009, "无法删除超级管理员账号"),
    NOT_ADMIN_OPERATE(-200010, "非管理员无法操作"),
    NOT_REMOVE_ADMIN_ROLE(-200011, "无法删除超级管理员角色"),
    ROLE_ASSIGNED_TO_USER(-200012, "角色已分配用户"),
    MOBILE_EXISTS(-200013, "手机号已存在"),
    NICKNAME_EXISTS(-200014, "昵称已存在"),
    ROLE_CODE_EXISTS(-200015, "角色标识已存在"),

    DICT_TYPE_EXISTS(-510001, "字段类型已存在"),
    DICT_DATA_NAME_EXISTS(-510002, "字典数据名称已存在"),
    DICT_DATA_VALUE_EXISTS(-510003, "字段数据值已存在"),

    CAPTCHA_LOAD_ERROR(-520001, "验证码获取失败"),
    CAPTCHA_EXPIRE_TIMEOUT(-520002, "验证码已失效"),
    CAPTCHA_VALID_ERROR(-520003, "验证码错误"),

    MISSING_PARAMS_ERROR(-530001, "缺少请求参数"),

    ;

    private int code;

    private String msg;

}
