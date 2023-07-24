package cn.p2nn.meteor.aspect;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.p2nn.meteor.entity.SysOperationLog;
import cn.p2nn.meteor.model.Result;
import cn.p2nn.meteor.service.SysOperationLogService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * 操作日志aop
 *
 * @author huangjiayao1993
 */
@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class OperationAspect {

    private final SysOperationLogService operationLogService;

    /**
     * 操作日志
     *
     * @param point
     * @throws Throwable
     */
    @Around("execution(* cn.p2nn.meteor.modules.system.web.*.*(..))")
    public Object operationAround(ProceedingJoinPoint point) throws Throwable {
        SysOperationLog entity = new SysOperationLog();
        long start = DateUtil.currentSeconds();
        Result proceed;
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            HandlerMethod handler = (HandlerMethod) (SpringUtil.getBean(RequestMappingHandlerMapping.class)).getHandler(request).getHandler();
            JSONObject data = new JSONObject();
            data.putAll(request.getParameterMap());
            String method = request.getMethod();
            if (!HttpMethod.GET.matches(method)) {
                Arrays.stream(point.getArgs()).forEach(item -> data.putAll(new JSONObject(item)));
            }
            entity.setController(handler.getBean().getClass().getName())
                    .setMethod(handler.getMethod().getName())
                    .setParamsJson(JSONUtil.toJsonStr(data))
                    .setCreateTime(LocalDateTime.now())
                    .setUrl(request.getRequestURI());
            proceed = (Result) point.proceed();
            entity.setSuccess(proceed.isSuccess()).setResponseJson(JSONUtil.toJsonStr(proceed));
        } catch (Exception e) {
            entity.setReason(e.getMessage());
            e.printStackTrace();
            throw e;
        } finally {
            if (StpUtil.isLogin()) {
                entity.setUserId(StpUtil.getLoginId().toString());
            }
            entity.setDuration(DateUtil.currentSeconds() - start);
            this.operationLogService.save(entity);
        }
        return proceed;
    }

}
