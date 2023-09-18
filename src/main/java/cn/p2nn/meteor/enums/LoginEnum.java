package cn.p2nn.meteor.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 第三方标识枚举
 *
 * @author huangjiayao1993
 */
@Getter
@AllArgsConstructor
public enum LoginEnum {

    LOGIN("login", "系统后台登录"),
    CLIENT("client", "客户端登录"),
    ;

    private final String type;

    private final String desc;

}
