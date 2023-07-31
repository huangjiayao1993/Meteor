package cn.p2nn.meteor.aspect;

import cn.hutool.json.JSONUtil;
import cn.p2nn.meteor.dto.LoginDto;
import cn.p2nn.meteor.entity.SysLoginLog;
import cn.p2nn.meteor.model.Result;
import cn.p2nn.meteor.service.CaptchaService;
import cn.p2nn.meteor.service.SysLoginLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 登录aop
 *
 * @author huangjiayao1993
 */
@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class LoginAspect {

    private final SysLoginLogService loginLogService;

    private final CaptchaService captchaService;

    /**
     * 登录日志
     *
     * @param point
     * @throws Throwable
     */
    @Around("execution(* cn.p2nn.meteor.web.AuthWeb.login(..))")
    public Object loginAround(ProceedingJoinPoint point) throws Throwable {
        SysLoginLog entity = new SysLoginLog();
        LoginDto dto = (LoginDto) point.getArgs()[0];
        entity.setUsername(dto.getUsername()).setLoginType(dto.getLoginType()).setCreateTime(LocalDateTime.now());
        Result proceed;
        try {
            this.captchaService.valid(dto.getUuid(), dto.getCode());
            proceed = (Result) point.proceed();
            entity.setSuccess(proceed.isSuccess()).setResponseJson(JSONUtil.toJsonStr(proceed));
        } catch (Exception e) {
            entity.setReason(e.getMessage());
            throw e;
        } finally {
            this.loginLogService.save(entity);
        }
        return proceed;
    }

}
