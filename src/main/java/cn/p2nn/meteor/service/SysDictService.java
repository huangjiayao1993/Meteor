package cn.p2nn.meteor.service;

import cn.hutool.core.lang.Assert;
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
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SysDictService extends ServiceImpl<SysDictMapper, SysDict> {

    private final SysDictDataService dictDataService;

    public PageResult page(Page page, SysDict dict) {
        LambdaQueryWrapper<SysDict> qw = Wrappers.lambdaQuery(SysDict.class)
                .like(StringUtils.isNotBlank(dict.getName()), SysDict::getName, dict.getName())
                .orderByDesc(SysDict::getId);
        page = this.page(page, qw);
        return PageResult.parse(page);
    }

    public void create(SysDict dict) {
        long count = this.count(Wrappers.lambdaQuery(SysDict.class).eq(SysDict::getType, dict.getType()));
        Assert.isFalse(count > 0, () -> {throw new UserException(ResultEnum.DICT_TYPE_EXISTS);});
        this.save(dict);
    }

    public void update(SysDict dict) {
        long count = this.count(Wrappers.lambdaQuery(SysDict.class).eq(SysDict::getType, dict.getType()).notIn(SysDict::getId, dict.getId()));
        Assert.isFalse(count > 0, () -> {throw new UserException(ResultEnum.DICT_TYPE_EXISTS);});
        this.updateById(dict);
    }

    public void remove(List<String> ids) {
        List<String> list = this.listByIds(ids).stream().map(SysDict::getType).collect(Collectors.toList());
        this.dictDataService.remove(Wrappers.lambdaQuery(SysDictData.class).in(SysDictData::getType, list));
        this.removeBatchByIds(ids);
    }
}
