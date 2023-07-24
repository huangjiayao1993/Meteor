package cn.p2nn.meteor.modules.system.web;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.p2nn.meteor.dto.AuthorizeDto;
import cn.p2nn.meteor.dto.IdsDto;
import cn.p2nn.meteor.entity.SysRole;
import cn.p2nn.meteor.model.PageResult;
import cn.p2nn.meteor.model.Result;
import cn.p2nn.meteor.service.SysRoleMenuService;
import cn.p2nn.meteor.service.SysRoleService;
import cn.p2nn.meteor.service.SysUserRoleService;
import cn.p2nn.meteor.web.BaseWeb;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 系统角色控制器
 *
 * @author huangjiayao1993
 */
@Slf4j
@RestController
@RequestMapping("system/role")
@RequiredArgsConstructor
public class RoleWeb extends BaseWeb {

    private final SysRoleService roleService;

    private final SysUserRoleService userRoleService;

    private final SysRoleMenuService roleMenuService;

    /**
     * 分页列表
     *
     * @param page
     * @param role
     * @return
     */
    @SaCheckPermission("sys:role:list")
    @GetMapping("page")
    public Result page(Page page, SysRole role) {
        PageResult result = this.roleService.page(page, role);
        return Result.success(result);
    }

    /**
     * 列表
     *
     * @param role
     * @return
     */
    @GetMapping("list")
    public Result list(SysRole role) {
        List<SysRole> list = this.roleService.list(role);
        return Result.success(list);
    }

    /**
     * 新增
     *
     * @param role
     * @return
     */
    @SaCheckPermission("sys:role:create")
    @PostMapping("create")
    public Result create(@RequestBody SysRole role) {
        this.roleService.create(role);
        return Result.success();
    }

    /**
     * 更新
     *
     * @param role
     * @return
     */
    @SaCheckPermission("sys:role:update")
    @PutMapping("update")
    public Result update(@RequestBody SysRole role) {
        this.roleService.update(role);
        return Result.success();
    }

    /**
     * 删除
     *
     * @param dto
     * @return
     */
    @SaCheckPermission("sys:role:remove")
    @DeleteMapping("remove")
    public Result remove(@RequestBody @Valid IdsDto dto) {
        this.roleService.remove(dto.getIds());
        return Result.success();
    }

    /**
     * 根据用户id获取角色列表
     *
     * @param userId
     * @return
     */
    @GetMapping("list/by/user/id")
    public Result listByUserId(@RequestParam String userId) {
        List<SysRole> list = this.userRoleService.listByUserId(userId);
        return Result.success(list);
    }

    /**
     * 授权菜单
     *
     * @param dto
     * @return
     */
    @SaCheckPermission("sys:role:authorize")
    @PostMapping("authorize")
    public Result authorize(@RequestBody @Valid AuthorizeDto dto) {
        this.roleMenuService.authorize(dto);
        return Result.success();
    }
}
