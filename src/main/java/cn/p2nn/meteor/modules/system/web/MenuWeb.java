package cn.p2nn.meteor.modules.system.web;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.p2nn.meteor.dto.IdsDto;
import cn.p2nn.meteor.entity.SysMenu;
import cn.p2nn.meteor.model.Result;
import cn.p2nn.meteor.service.SysMenuService;
import cn.p2nn.meteor.service.SysRoleMenuService;
import cn.p2nn.meteor.web.BaseWeb;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 系统菜单控制器
 *
 * @author huangjiayao1993
 */
@Slf4j
@RestController
@RequestMapping("system/menu")
@RequiredArgsConstructor
public class MenuWeb extends BaseWeb {

    private final SysMenuService menuService;

    private final SysRoleMenuService roleMenuService;

    /**
     * 获取当前登录用户菜单列表
     *
     * @param menu
     * @return
     */
    @GetMapping("current/user/menu")
    public Result currentUserMenu(SysMenu menu) {
        List<SysMenu> list = this.menuService.currentUserMenu(menu);
        return Result.success(list);
    }

    /**
     * 树形列表
     *
     * @param menu
     * @return
     */
    @GetMapping("tree")
    public Result tree(SysMenu menu) {
        List<SysMenu> list = this.menuService.tree(menu);
        return Result.success(list);
    }

    /**
     * 新增
     *
     * @param menu
     * @return
     */
    @SaCheckPermission("sys:menu:create")
    @PostMapping("create")
    public Result create(@RequestBody SysMenu menu) {
        this.menuService.save(menu);
        return Result.success();
    }

    /**
     * 更新
     *
     * @param menu
     * @return
     */
    @SaCheckPermission("sys:menu:update")
    @PutMapping("update")
    public Result update(@RequestBody SysMenu menu) {
        this.menuService.updateById(menu);
        return Result.success();
    }

    /**
     * 删除
     *
     * @param dto
     * @return
     */
    @SaCheckPermission("sys:menu:remove")
    @DeleteMapping("remove")
    public Result remove(@RequestBody @Valid IdsDto dto) {
        this.menuService.remove(dto.getIds());
        return Result.success();
    }

    /**
     * 根据角色id获取菜单列表
     *
     * @param roleId
     * @return
     */
    @GetMapping("list/by/role/id")
    public Result listByRoleId(@RequestParam String roleId) {
        List<SysMenu> list = this.roleMenuService.listByRoleId(roleId);
        return Result.success(list);
    }
}
