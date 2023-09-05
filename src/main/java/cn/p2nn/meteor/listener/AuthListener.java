package cn.p2nn.meteor.listener;

import cn.dev33.satoken.listener.SaTokenListenerForSimple;
import cn.dev33.satoken.stp.SaLoginModel;
import cn.hutool.core.util.StrUtil;
import cn.p2nn.meteor.constants.CacheConstant;
import cn.p2nn.meteor.entity.SysUser;
import cn.p2nn.meteor.service.RedisService;
import cn.p2nn.meteor.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 认证监听器
 *
 * @author huangjiayao1993
 */
@Component
@RequiredArgsConstructor
public class AuthListener extends SaTokenListenerForSimple {

    private final RedisService redisService;

    private final SysUserService userService;

    @Override
    public void doLogin(String loginType, Object loginId, String tokenValue, SaLoginModel loginModel) {
        SysUser user = this.userService.getById(loginId.toString());
        user.setLoginTime(LocalDateTime.now());
        this.userService.updateById(user);
    }

    @Override
    public void doLogout(String loginType, Object loginId, String tokenValue) {
        redisService.delete(StrUtil.join(CacheConstant.PERMISSION_KEY, loginId));
        redisService.delete(StrUtil.join(CacheConstant.ROLE_KEY, loginId));
    }
}
