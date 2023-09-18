package cn.p2nn.meteor.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultEnum {

    SUCCESS(0, "成功"),
    FAILED(-100000, "失败"),
    AUTH_FAILED(-100001, "Token认证异常"),
    LOGIN_TYPE_FAILED(-100002, "登录类型错误"),

    ACCOUNT_NOT_FOUND(-200000, "账号不存在"),
    USER_NOT_FOUND(-200001, "用户不存在"),
    USERNAME_PASSWORD_ERROR(-200002, "用户名或密码错误"),
    USER_DEACTIVATED(-200003, "用户已停用"),
    USER_NOTIN_USE_TIME(-200004, "用户不在使用期限内"),
    LOGIN_ERROR(-200005, "登录异常"),
    PERMISSION_ERROR(-200006, "权限异常"),
    ROLE_ERROR(-200007, "角色异常"),
    USERNAME_EXISTS(-200008, "用户名已存在"),
    OLD_PASSWORD_ERROR(-200009, "原密码错误"),
    NOT_REMOVE_ADMIN(-200010, "无法删除超级管理员账号"),
    NOT_ADMIN_OPERATE(-200011, "非管理员无法操作"),
    NOT_REMOVE_ADMIN_ROLE(-200012, "无法删除超级管理员角色"),
    ROLE_ASSIGNED_TO_USER(-200013, "角色已分配用户"),
    MOBILE_EXISTS(-200014, "手机号已存在"),
    NICKNAME_EXISTS(-200015, "昵称已存在"),
    ROLE_CODE_EXISTS(-200016, "角色标识已存在"),
    ORG_USER_EXISTS(-200017, "该组织存在用户"),
    RSA_PRIVATE_NOT_FOUND(-200018, "RSA私钥未配置"),
    RSA_PUBLIC_NOT_FOUND(-200019, "RSA公钥未配置"),
    AES_PRIVATE_NOT_FOUND(-200020, "AES私钥未配置"),
    RSA_VALID_ERROR(-200021, "RSA解密失败"),
    AES_VALID_ERROR(-200022, "AES解密失败"),

    WX_CONFIG_NOT_FOUND(-300000, "微信配置不存在"),
    WX_OPENID_GET_ERROR(-300001, "微信openId获取失败"),

    DICT_TYPE_EXISTS(-510001, "字段类型已存在"),
    DICT_DATA_NAME_EXISTS(-510002, "字典数据名称已存在"),
    DICT_DATA_VALUE_EXISTS(-510003, "字段数据值已存在"),

    CAPTCHA_LOAD_ERROR(-520001, "验证码获取失败"),
    CAPTCHA_EXPIRE_TIMEOUT(-520002, "验证码已失效"),
    CAPTCHA_VALID_ERROR(-520003, "验证码错误"),

    MISSING_PARAMS_ERROR(-530001, "缺少请求参数"),
    IO_ERROR(-530002, "IO操作异常"),

    MISSING_OSS_DEFAULT_ERROR(-540001, "缺少OSS默认类型"),

    ;

    private int code;

    private String msg;

}
