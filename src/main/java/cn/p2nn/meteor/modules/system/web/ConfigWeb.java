package cn.p2nn.meteor.modules.system.web;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.p2nn.meteor.dto.IdsDto;
import cn.p2nn.meteor.entity.SysConfig;
import cn.p2nn.meteor.model.PageResult;
import cn.p2nn.meteor.model.Result;
import cn.p2nn.meteor.service.RedisService;
import cn.p2nn.meteor.service.SysConfigService;
import cn.p2nn.meteor.web.BaseWeb;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 系统配置控制器
 *
 * @author huangjiayao1993
 */
@Slf4j
@RestController
@RequestMapping("system/config")
@RequiredArgsConstructor
public class ConfigWeb extends BaseWeb {

    private final SysConfigService configService;

    /**
     * 刷新缓存
     *
     * @return
     */
    @SaCheckPermission("sys:config:refresh")
    @PostMapping("refresh")
    public Result refresh() {
        this.configService.refresh();
        return Result.success();
    }

    /**
     * 清空缓存
     *
     * @return
     */
    @SaCheckPermission("sys:config:refresh")
    @PostMapping("clean")
    public Result clean() {
        this.configService.clean();
        return Result.success();
    }

    /**
     * 分页列表
     *
     * @param page
     * @param config
     * @return
     */
    @SaCheckPermission("sys:config:list")
    @GetMapping("page")
    public Result page(Page page, SysConfig config) {
        PageResult result = this.configService.page(page, config);
        return Result.success(result);
    }

    /**
     * 新增
     *
     * @param config
     * @return
     */
    @SaCheckPermission("sys:config:create")
    @PostMapping("create")
    public Result create(@RequestBody @Valid SysConfig config) {
        this.configService.save(config);
        return Result.success();
    }

    /**
     * 更新
     *
     * @param config
     * @return
     */
    @SaCheckPermission("sys:config:update")
    @PutMapping("update")
    public Result update(@RequestBody SysConfig config) {
        this.configService.updateById(config);
        return Result.success();
    }

    /**
     * 删除
     *
     * @param dto
     * @return
     */
    @SaCheckPermission("sys:config:remove")
    @DeleteMapping("remove")
    public Result remove(@RequestBody @Valid IdsDto dto) {
        this.configService.removeBatchByIds(dto.getIds());
        return Result.success();
    }

}
