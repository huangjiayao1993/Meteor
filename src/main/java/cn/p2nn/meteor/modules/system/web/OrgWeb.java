package cn.p2nn.meteor.modules.system.web;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.p2nn.meteor.dto.IdsDto;
import cn.p2nn.meteor.entity.SysOrg;
import cn.p2nn.meteor.model.PageResult;
import cn.p2nn.meteor.model.Result;
import cn.p2nn.meteor.service.SysOrgService;
import cn.p2nn.meteor.web.BaseWeb;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 系统组织控制器
 *
 * @author huangjiayao1993
 */
@Slf4j
@RestController
@RequestMapping("system/org")
@RequiredArgsConstructor
public class OrgWeb extends BaseWeb {

    private final SysOrgService orgService;

    /**
     * 树形列表
     *
     * @param org
     * @return
     */
    @GetMapping("tree")
    public Result tree(SysOrg org) {
        List<SysOrg> list = this.orgService.tree(org);
        return Result.success(list);
    }

    /**
     * 列表
     *
     * @param page
     * @param org
     * @return
     */
    @SaCheckPermission("system:org:list")
    @GetMapping("page")
    public Result page(Page page, SysOrg org) {
        PageResult result = this.orgService.page(page, org);
        return Result.success(result);
    }

    /**
     * 新增
     *
     * @param org
     * @return
     */
    @SaCheckPermission("system:org:create")
    @PostMapping("create")
    public Result create(@RequestBody SysOrg org) {
        this.orgService.save(org);
        return Result.success();
    }

    /**
     * 更新
     *
     * @param org
     * @return
     */
    @SaCheckPermission("system:org:update")
    @PutMapping("update")
    public Result update(@RequestBody SysOrg org) {
        this.orgService.updateById(org);
        return Result.success();
    }

    /**
     * 删除
     *
     * @param dto
     * @return
     */
    @SaCheckPermission("system:org:remove")
    @DeleteMapping("remove")
    public Result remove(@RequestBody @Valid IdsDto dto) {
        this.orgService.removeBatchByIds(dto.getIds());
        return Result.success();
    }

}
