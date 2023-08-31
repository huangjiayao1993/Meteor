package cn.p2nn.meteor.aspect;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import cn.p2nn.meteor.config.MeteorConfig;
import cn.p2nn.meteor.dto.LoginDto;
import cn.p2nn.meteor.enums.ResultEnum;
import cn.p2nn.meteor.exception.BusinessException;
import cn.p2nn.meteor.service.CaptchaService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 登录aop
 *
 * @author huangjiayao1993
 */
@Order(0)
@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class LoginBeforeAspect {

    private final MeteorConfig config;

    private final CaptchaService captchaService;

    /**
     * 登录前置
     *
     * @param point
     */
    @Before("execution(* cn.p2nn.meteor.web.AuthWeb.login(..))")
    public void loginBefore(JoinPoint point) {
        LoginDto dto = (LoginDto) point.getArgs()[0];
        try {
            RSA rsa = SecureUtil.rsa(config.getRsaPrivate(), config.getRsaPublic());
            dto.setUsername(rsa.decryptStr(dto.getUsername(), KeyType.PrivateKey));
            dto.setPassword(rsa.decryptStr(dto.getPassword(), KeyType.PrivateKey));
        } catch (Exception e) {
            throw new BusinessException(ResultEnum.RSA_VALID_ERROR);
        }
        this.captchaService.valid(dto.getUuid(), dto.getCode());
    }

}
