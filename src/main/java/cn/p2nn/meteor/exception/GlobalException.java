package cn.p2nn.meteor.exception;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import cn.p2nn.meteor.enums.ResultEnum;
import cn.p2nn.meteor.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常
 *
 * @author huangjiayao1993
 */
@Slf4j
@RestControllerAdvice
public class GlobalException {

    /**
     * 默认异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Result handlerException(Exception e) {
        log.error(e.getMessage(), e);
        return Result.failed(ResultEnum.FAILED);
    }

    /**
     * 自定义-业务异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    public Result handlerBusinessException(BusinessException e) {
        return Result.failed(e.getCode(), e.getMsg());
    }

    /**
     * 自定义-认证异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(AuthException.class)
    public Result handlerAuthException(AuthException e) {
        return Result.failed(e.getCode(), e.getMsg());
    }

    /**
     * 自定义-用户异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(UserException.class)
    public Result handlerUserException(UserException e) {
        return Result.failed(e.getCode(), e.getMsg());
    }

    /**
     * 未登录异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(NotLoginException.class)
    public Result handlerException(NotLoginException e) {
        return Result.failed(ResultEnum.LOGIN_ERROR.getCode(), e.getMessage());
    }

    /**
     * 缺少权限异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(NotPermissionException.class)
    public Result handlerException(NotPermissionException e) {
        return Result.failed(ResultEnum.PERMISSION_ERROR.getCode(), e.getMessage());
    }

    /**
     * 缺少角色异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(NotRoleException.class)
    public Result handlerException(NotRoleException e) {
        return Result.failed(ResultEnum.ROLE_ERROR.getCode(), e.getMessage());
    }

    /**
     * 缺少参数异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public Result handlerIllegalArgumentException(IllegalArgumentException e) {
        return Result.failed(ResultEnum.MISSING_PARAMS_ERROR);
    }

}
