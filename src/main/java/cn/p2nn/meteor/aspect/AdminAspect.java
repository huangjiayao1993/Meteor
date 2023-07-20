package cn.p2nn.meteor.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.lang.Assert;
import cn.p2nn.meteor.constants.CommonConstant;
import cn.p2nn.meteor.dto.AuthorizeDto;
import cn.p2nn.meteor.dto.IdsDto;
import cn.p2nn.meteor.entity.SysRole;
import cn.p2nn.meteor.entity.SysUser;
import cn.p2nn.meteor.enums.ResultEnum;
import cn.p2nn.meteor.exception.UserException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 管理员操作aop
 *
 * @author huangjiayao1993
 */
@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class AdminAspect {

    /**
     * 用户-更新
     *
     * @param point
     */
    @Before("execution(* cn.p2nn.meteor.modules.system.web.UserWeb.update(..))")
    public void updateUserBefore(JoinPoint point) {
        SysUser user = (SysUser) point.getArgs()[0];
        boolean updateAdminUser = !CommonConstant.ADMIN_ID.equals(StpUtil.getLoginId()) && user.isAdmin();
        Assert.isFalse(updateAdminUser, () -> {
            throw new UserException(ResultEnum.NOT_ADMIN_OPERATE);
        });
    }

    /**
     * 用户-删除
     *
     * @param point
     */
    @Before("execution(* cn.p2nn.meteor.modules.system.web.UserWeb.remove(..))")
    public void removeUserBefore(JoinPoint point) {
        IdsDto dto = (IdsDto) point.getArgs()[0];
        boolean match = dto.getIds().stream().anyMatch(id -> CommonConstant.ADMIN_ID.equals(id));
        Assert.isFalse(match, () -> {
            throw new UserException(ResultEnum.NOT_REMOVE_ADMIN);
        });
    }

    /**
     * 用户-授权角色
     *
     * @param point
     */
    @Before("execution(* cn.p2nn.meteor.modules.system.web.UserWeb.authorize(..))")
    public void authorizeUserBefore(JoinPoint point) {
        AuthorizeDto dto = (AuthorizeDto) point.getArgs()[0];
        boolean updateAdminUser = !CommonConstant.ADMIN_ID.equals(StpUtil.getLoginId()) && CommonConstant.ADMIN_ID.equals(dto.getId());
        Assert.isFalse(updateAdminUser, () -> {
            throw new UserException(ResultEnum.NOT_ADMIN_OPERATE);
        });
    }

    /**
     * 用户-重置密码
     *
     * @param point
     */
    @Before("execution(* cn.p2nn.meteor.modules.system.web.UserWeb.resetPassword(..))")
    public void resetPasswordUserBefore(JoinPoint point) {
        IdsDto dto = (IdsDto) point.getArgs()[0];
        boolean match = dto.getIds().stream().anyMatch(id -> CommonConstant.ADMIN_ID.equals(id));
        boolean updateAdminUser = !CommonConstant.ADMIN_ID.equals(StpUtil.getLoginId()) && match;
        Assert.isFalse(updateAdminUser, () -> {
            throw new UserException(ResultEnum.NOT_ADMIN_OPERATE);
        });
    }

    /**
     * 用户-修改密码
     *
     * @param point
     */
    @Before("execution(* cn.p2nn.meteor.modules.system.web.UserWeb.changePassword(..))")
    public void changePasswordUserBefore(JoinPoint point) {
        SysUser user = (SysUser) point.getArgs()[0];
        boolean updateAdminUser = !CommonConstant.ADMIN_ID.equals(StpUtil.getLoginId()) && user.isAdmin();
        Assert.isFalse(updateAdminUser, () -> {
            throw new UserException(ResultEnum.NOT_ADMIN_OPERATE);
        });
    }

    /**
     * 用户-修改状态
     *
     * @param point
     */
    @Before("execution(* cn.p2nn.meteor.modules.system.web.UserWeb.changeStatus(..))")
    public void changeStatusUserBefore(JoinPoint point) {
        SysUser user = (SysUser) point.getArgs()[0];
        boolean updateAdminUser = !CommonConstant.ADMIN_ID.equals(StpUtil.getLoginId()) && user.isAdmin();
        Assert.isFalse(updateAdminUser, () -> {
            throw new UserException(ResultEnum.NOT_ADMIN_OPERATE);
        });
    }

    /**
     * 角色-更新
     *
     * @param point
     */
    @Before("execution(* cn.p2nn.meteor.modules.system.web.RoleWeb.update(..))")
    public void updateRoleBefore(JoinPoint point) {
        SysRole role = (SysRole) point.getArgs()[0];
        boolean updateAdminRole = !CommonConstant.ADMIN_ID.equals(StpUtil.getLoginId()) && role.isAdmin();
        Assert.isFalse(updateAdminRole, () -> {
            throw new UserException(ResultEnum.NOT_ADMIN_OPERATE);
        });
    }

    /**
     * 角色-删除
     *
     * @param point
     */
    @Before("execution(* cn.p2nn.meteor.modules.system.web.RoleWeb.remove(..))")
    public void removeRoleBefore(JoinPoint point) {
        IdsDto dto = (IdsDto) point.getArgs()[0];
        boolean match = dto.getIds().stream().anyMatch(id -> CommonConstant.ADMIN_ROLE_ID.equals(id));
        Assert.isFalse(match, () -> {
            throw new UserException(ResultEnum.NOT_REMOVE_ADMIN_ROLE);
        });
    }

    /**
     * 角色-授权菜单
     *
     * @param point
     */
    @Before("execution(* cn.p2nn.meteor.modules.system.web.RoleWeb.authorize(..))")
    public void authorizeRoleBefore(JoinPoint point) {
        AuthorizeDto dto = (AuthorizeDto) point.getArgs()[0];
        boolean updateAdminUser = !CommonConstant.ADMIN_ID.equals(StpUtil.getLoginId()) && CommonConstant.ADMIN_ROLE_ID.equals(dto.getId());
        Assert.isFalse(updateAdminUser, () -> {
            throw new UserException(ResultEnum.NOT_ADMIN_OPERATE);
        });
    }
}
