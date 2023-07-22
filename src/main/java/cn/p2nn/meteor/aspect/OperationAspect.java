package cn.p2nn.meteor.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import cn.p2nn.meteor.model.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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

    /**
     * 操作日志
     *
     * @param point
     * @throws Throwable
     */
    @Around("execution(* cn.p2nn.meteor.modules.system.web.*.*(..))")
    public Object operationAround(ProceedingJoinPoint point) throws Throwable {
        Result proceed;
        try {
            proceed = (Result) point.proceed();
        } catch (Exception e) {
            throw e;
        } finally {
        }
        return proceed;
    }

}
