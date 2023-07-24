package cn.p2nn.meteor.modules.system.web;

import cn.p2nn.meteor.dto.IdsDto;
import cn.p2nn.meteor.entity.SysDict;
import cn.p2nn.meteor.entity.SysDictData;
import cn.p2nn.meteor.model.PageResult;
import cn.p2nn.meteor.model.Result;
import cn.p2nn.meteor.service.SysDictDataService;
import cn.p2nn.meteor.service.SysDictService;
import cn.p2nn.meteor.web.BaseWeb;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统字典控制器
 *
 * @author huangjiayao1993
 */
@Slf4j
@RestController
@RequestMapping("system/dict")
@RequiredArgsConstructor
public class DictWeb extends BaseWeb {

    private final SysDictService dictService;

    private final SysDictDataService dictDataService;

    /**
     * 字典类型-分页列表
     *
     * @param page
     * @param dict
     * @return
     */
    @GetMapping("type/page")
    public Result typePage(Page page, SysDict dict) {
        PageResult result = this.dictService.page(page, dict);
        return Result.success(result);
    }

    /**
     * 字典类型-新增
     *
     * @param dict
     * @return
     */
    @GetMapping("type/create")
    public Result typeCreate(@RequestBody @Valid SysDict dict) {
        this.dictService.create(dict);
        return Result.success();
    }

    /**
     * 字典类型-更新
     *
     * @param dict
     * @return
     */
    @GetMapping("type/update")
    public Result typeUpdate(@RequestBody SysDict dict) {
        this.dictService.update(dict);
        return Result.success();
    }

    /**
     * 字典类型-删除
     *
     * @param dto
     * @return
     */
    @GetMapping("type/remove")
    public Result typeRemove(@RequestBody @Valid IdsDto dto) {
        this.dictService.remove(dto.getIds());
        return Result.success();
    }

    /**
     * 字典数据-分页列表
     *
     * @param page
     * @param data
     * @return
     */
    @GetMapping("data/page")
    public Result dataPage(Page page, SysDictData data) {
        PageResult result = this.dictDataService.page(page, data);
        return Result.success(result);
    }

    /**
     * 字典数据-新增
     *
     * @param data
     * @return
     */
    @GetMapping("data/create")
    public Result dataCreate(@RequestBody @Valid SysDictData data) {
        this.dictDataService.create(data);
        return Result.success();
    }

    /**
     * 字典数据-更新
     *
     * @param data
     * @return
     */
    @GetMapping("data/update")
    public Result dataUpdate(@RequestBody SysDictData data) {
        this.dictDataService.update(data);
        return Result.success();
    }

    /**
     * 字典数据-删除
     *
     * @param dto
     * @return
     */
    @GetMapping("data/remove")
    public Result dataRemove(@RequestBody @Valid IdsDto dto) {
        this.dictDataService.remove(dto.getIds());
        return Result.success();
    }
}
