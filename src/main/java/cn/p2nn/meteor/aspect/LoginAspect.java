package cn.p2nn.meteor.aspect;

import java.time.LocalDateTime;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.hutool.json.JSONUtil;
import cn.p2nn.meteor.dto.LoginDto;
import cn.p2nn.meteor.entity.SysLoginLog;
import cn.p2nn.meteor.entity.SysUser;
import cn.p2nn.meteor.model.Result;
import cn.p2nn.meteor.service.SysLoginLogService;
import cn.p2nn.meteor.service.SysUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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

    private final SysUserService userService;

    private final SysLoginLogService loginLogService;

    /**
     * 登录正常返回
     *
     * @param point
     * @param result
     */
    @AfterReturning(value = "execution(* cn.p2nn.meteor.web.AuthWeb.login(..))", returning = "result")
    public void loginAfterReturning(JoinPoint point, Result result) {
        SaTokenInfo tokenInfo = (SaTokenInfo) result.getData();
        SysUser user = this.userService.getById(tokenInfo.getLoginId().toString());
        user.setLoginTime(LocalDateTime.now());
        this.userService.updateById(user);
    }

    
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
