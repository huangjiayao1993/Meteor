package cn.p2nn.meteor.listener;

import cn.dev33.satoken.listener.SaTokenListenerForSimple;
import cn.dev33.satoken.stp.SaLoginModel;
import cn.p2nn.meteor.constants.CacheConstant;
import cn.p2nn.meteor.entity.SysUser;
import cn.p2nn.meteor.modules.client.entity.CtMember;
import cn.p2nn.meteor.service.LoginTypeService;
import cn.p2nn.meteor.service.RedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 认证监听器
 *
 * @author huangjiayao1993
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class AuthListener extends SaTokenListenerForSimple {

    private final RedisService redisService;

    private final LoginTypeService loginTypeService;

    @Override
    public void doLogin(String loginType, Object loginId, String tokenValue, SaLoginModel loginModel) {
        this.loginTypeService.switchType(loginType, Boolean.TRUE,
                (service) -> service.lambdaUpdate().eq(SysUser::getId, loginId).set(SysUser::getLoginTime, LocalDateTime.now()).update(),
                (service) -> service.lambdaUpdate().eq(CtMember::getId, loginId).set(CtMember::getLoginTime, LocalDateTime.now()).update()
        );
    }

    @Override
    public void doLogout(String loginType, Object loginId, String tokenValue) {
        this.redisService.delete(StringUtils.join(CacheConstant.PERMISSION_KEY, loginId));
        this.redisService.delete(StringUtils.join(CacheConstant.ROLE_KEY, loginId));
    }
}
