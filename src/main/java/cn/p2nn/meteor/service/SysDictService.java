package cn.p2nn.meteor.service;

import cn.hutool.core.lang.Assert;
import cn.p2nn.meteor.constants.CacheConstant;
import cn.p2nn.meteor.entity.SysDict;
import cn.p2nn.meteor.entity.SysDictData;
import cn.p2nn.meteor.enums.ResultEnum;
import cn.p2nn.meteor.exception.UserException;
import cn.p2nn.meteor.mapper.SysDictMapper;
import cn.p2nn.meteor.model.PageResult;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SysDictService extends ServiceImpl<SysDictMapper, SysDict> {

    private final RedisService redisService;

    private final SysDictDataService dictDataService;

    @PostConstruct
    public void init() {
        List<SysDict> list = this.list();
        list.stream().forEach(item -> {
            List<SysDictData> dataList = this.dictDataService.listByType(item.getType());
            if (!dataList.isEmpty()) {
                this.redisService.setList(StringUtils.join(CacheConstant.DICT_KEY, item.getType()), dataList);
            }
        });
    }

    public void refresh() {
        this.clean();
        this.init();
    }

    public void clean() {
        this.redisService.delete(StringUtils.join(CacheConstant.DICT_KEY, "*"));
    }

    public PageResult page(Page page, SysDict dict) {
        LambdaQueryWrapper<SysDict> qw = Wrappers.lambdaQuery(SysDict.class)
                .like(StringUtils.isNotBlank(dict.getName()), SysDict::getName, dict.getName())
                .orderByDesc(SysDict::getId);
        page = this.page(page, qw);
        return PageResult.parse(page);
    }

    @Transactional(rollbackFor = Exception.class)
    public void create(SysDict dict) {
        long count = this.count(Wrappers.lambdaQuery(SysDict.class).eq(SysDict::getType, dict.getType()));
        Assert.isFalse(count > 0, () -> {throw new UserException(ResultEnum.DICT_TYPE_EXISTS);});
        this.save(dict);
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(SysDict dict) {
        long count = this.count(Wrappers.lambdaQuery(SysDict.class).eq(SysDict::getType, dict.getType()).notIn(SysDict::getId, dict.getId()));
        Assert.isFalse(count > 0, () -> {throw new UserException(ResultEnum.DICT_TYPE_EXISTS);});
        this.updateById(dict);
    }

    @Transactional(rollbackFor = Exception.class)
    public void remove(List<String> ids) {
        List<String> list = this.listByIds(ids).stream().map(SysDict::getType).collect(Collectors.toList());
        List<String> dataIds = this.dictDataService.list(Wrappers.lambdaQuery(SysDictData.class).in(SysDictData::getType, list)).stream().map(SysDictData::getId).collect(Collectors.toList());
        this.dictDataService.remove(dataIds);
        this.removeBatchByIds(ids);
    }
}
