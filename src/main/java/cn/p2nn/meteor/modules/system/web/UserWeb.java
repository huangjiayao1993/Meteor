package cn.p2nn.meteor.modules.system.web;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.p2nn.meteor.dto.AuthorizeDto;
import cn.p2nn.meteor.dto.IdsDto;
import cn.p2nn.meteor.entity.SysUser;
import cn.p2nn.meteor.model.PageResult;
import cn.p2nn.meteor.model.Result;
import cn.p2nn.meteor.service.SysUserRoleService;
import cn.p2nn.meteor.service.SysUserService;
import cn.p2nn.meteor.web.BaseWeb;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 系统用户控制器
 *
 * @author huangjiayao1993
 */
@Slf4j
@RestController
@RequestMapping("system/user")
@RequiredArgsConstructor
public class UserWeb extends BaseWeb {

    private final SysUserService userService;

    private final SysUserRoleService userRoleService;

    /**
     * 分页列表
     *
     * @param page
     * @param user
     * @return
     */
    @SaCheckPermission("sys:user:list")
    @GetMapping("page")
    public Result page(Page page, SysUser user) {
        PageResult result = this.userService.page(page, user);
        return Result.success(result);
    }

    /**
     * 新增
     *
     * @param user
     * @return
     */
    @SaCheckPermission("sys:user:create")
    @PostMapping("create")
    public Result create(@RequestBody SysUser user) {
        this.userService.create(user);
        return Result.success();
    }

    /**
     * 更新
     *
     * @param user
     * @return
     */
    @SaCheckPermission("sys:user:update")
    @PutMapping("update")
    public Result update(@RequestBody SysUser user) {
        this.userService.update(user);
        return Result.success();
    }

    /**
     * 删除
     *
     * @param dto
     * @return
     */
    @SaCheckPermission("sys:user:remove")
    @DeleteMapping("remove")
    public Result remove(@RequestBody @Valid IdsDto dto) {
        this.userService.remove(dto.getIds());
        return Result.success();
    }

    /**
     * 授权角色
     *
     * @param dto
     * @return
     */
    @SaCheckPermission("sys:user:authorize")
    @PostMapping("authorize")
    public Result authorize(@RequestBody @Valid AuthorizeDto dto) {
        this.userRoleService.authorize(dto);
        return Result.success();
    }

    /**
     * 重置密码
     *
     * @param dto
     * @return
     */
    @SaCheckPermission("sys:user:resetPassword")
    @PutMapping("reset/password")
    public Result resetPassword(@RequestBody @Valid IdsDto dto) {
        this.userService.resetPassword(dto.getIds());
        return Result.success();
    }

    /**
     * 修改密码
     *
     * @param user
     * @return
     */
    @SaCheckPermission("sys:user:changePassword")
    @PutMapping("change/password")
    public Result changePassword(@RequestBody SysUser user) {
        this.userService.changePassword(user.getAccountId(), user.getPassword(), user.getNewPassword());
        return Result.success();
    }

    /**
     * 修改状态
     *
     * @param user
     * @return
     */
    @SaCheckPermission("sys:user:changeStatus")
    @PutMapping("change/status")
    public Result changeStatus(@RequestBody SysUser user) {
        this.userService.updateById(user);
        return Result.success();
    }
}
