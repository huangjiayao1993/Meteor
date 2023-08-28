package cn.p2nn.meteor.aspect;

import cn.p2nn.meteor.dto.LoginDto;
import cn.p2nn.meteor.service.CaptchaService;
import lombok.RequiredArgsConstructor;
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

    private final CaptchaService captchaService;

    /**
     * 登录前置
     *
     * @param point
     */
    @Before("execution(* cn.p2nn.meteor.web.AuthWeb.login(..))")
    public void loginBefore(JoinPoint point) {
        LoginDto dto = (LoginDto) point.getArgs()[0];
        this.captchaService.valid(dto.getUuid(), dto.getCode());
    }

}
