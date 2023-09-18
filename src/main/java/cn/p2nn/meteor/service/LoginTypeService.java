package cn.p2nn.meteor.service;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.EnumUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.extra.spring.SpringUtil;
import cn.p2nn.meteor.enums.LoginEnum;
import cn.p2nn.meteor.enums.ResultEnum;
import cn.p2nn.meteor.exception.AuthException;
import cn.p2nn.meteor.modules.client.service.CtMemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginTypeService {

    public void switchType(String loginType, boolean isThrow, LoginCallback loginCallback, ClientCallback clientCallback) {
        LoginEnum e = EnumUtil.fromStringQuietly(LoginEnum.class, StringUtils.upperCase(loginType));
        switch (e) {
            case LOGIN -> {
                if (ObjectUtil.isNotNull(loginCallback)) {
                    loginCallback.apply(SpringUtil.getBean(SysUserService.class));
                }
            }
            case CLIENT -> {
                if (ObjectUtil.isNotNull(clientCallback)) {
                    clientCallback.apply(SpringUtil.getBean(CtMemberService.class));
                }
            }
            default -> Assert.isTrue(isThrow, () -> {throw new AuthException(ResultEnum.LOGIN_TYPE_FAILED);});
        }
    }

    public interface LoginCallback {
        void apply(SysUserService userService);
    }

    public interface ClientCallback {
        void apply(CtMemberService memberService);
    }
}
