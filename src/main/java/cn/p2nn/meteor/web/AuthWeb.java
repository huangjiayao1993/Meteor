package cn.p2nn.meteor.web;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.lang.Assert;
import cn.p2nn.meteor.dto.LoginDto;
import cn.p2nn.meteor.entity.SysUser;
import cn.p2nn.meteor.enums.ResultEnum;
import cn.p2nn.meteor.exception.AuthException;
import cn.p2nn.meteor.model.Result;
import cn.p2nn.meteor.service.SysUserService;
import cn.p2nn.meteor.vo.AuthPermissionVo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 登录权限控制器
 *
 * @author huangjiayao1993
 */
@Slf4j
@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthWeb extends BaseWeb {

    private final SysUserService userService;

    /**
     * 登录
     *
     * @param dto
     * @return
     */
    @PostMapping("login")
    public Result login(@RequestBody @Valid LoginDto dto) {
        SysUser user = this.userService.getByUsername(dto.getUsername());
        Assert.notNull(user, () -> {
            throw new AuthException(ResultEnum.USERNAME_PASSWORD_ERROR);
        });
        SysUserService.checkPassword(dto.getPassword(), user.getPassword());
        this.userService.checkLogin(user);
        StpUtil.login(user.getId());
        return Result.success(StpUtil.getTokenInfo());
    }

    /**
     * 获取当前用户信息和权限
     *
     * @return
     */
    @GetMapping("current")
    public Result current() {
        if (!StpUtil.isLogin()) {
            return Result.failed();
        }
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        SysUser user = this.userService.getById(tokenInfo.getLoginId().toString());
        AuthPermissionVo vo = new AuthPermissionVo(user, StpUtil.getRoleList(), StpUtil.getPermissionList());
        return Result.success(vo);
    }

    /**
     * 注销
     *
     * @return
     */
    @DeleteMapping("logout")
    public Result logout() {
        StpUtil.logout();
        return Result.success();
    }
}