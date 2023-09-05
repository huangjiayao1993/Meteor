package cn.p2nn.meteor.service;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import cn.p2nn.meteor.entity.SysDictData;
import cn.p2nn.meteor.enums.ResultEnum;
import cn.p2nn.meteor.exception.UserException;
import cn.p2nn.meteor.mapper.SysDictDataMapper;
import cn.p2nn.meteor.model.PageResult;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SysDictDataService extends ServiceImpl<SysDictDataMapper, SysDictData> {

    public PageResult page(Page page, SysDictData data) {
        LambdaQueryWrapper<SysDictData> qw = Wrappers.lambdaQuery(SysDictData.class)
                .eq(StrUtil.isNotBlank(data.getType()), SysDictData::getType, data.getType())
                .like(StrUtil.isNotBlank(data.getName()), SysDictData::getName, data.getName());
        page = this.page(page, qw);
        return PageResult.parse(page);
    }

    @Transactional(rollbackFor = Exception.class)
    public void create(SysDictData data) {
        long nameCount = this.count(Wrappers.lambdaQuery(SysDictData.class).eq(SysDictData::getType, data.getType()).eq(SysDictData::getName, data.getName()));
        Assert.isFalse(nameCount > 0, () -> {throw new UserException(ResultEnum.DICT_DATA_NAME_EXISTS);});
        long valueCount = this.count(Wrappers.lambdaQuery(SysDictData.class).eq(SysDictData::getType, data.getType()).eq(SysDictData::getValue, data.getValue()));
        Assert.isFalse(valueCount > 0, () -> {throw new UserException(ResultEnum.DICT_DATA_VALUE_EXISTS);});
        this.save(data);
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(SysDictData data) {
        long nameCount = this.count(Wrappers.lambdaQuery(SysDictData.class).eq(SysDictData::getType, data.getType()).eq(SysDictData::getName, data.getName()).notIn(SysDictData::getId, data.getId()));
        Assert.isFalse(nameCount > 0, () -> {throw new UserException(ResultEnum.DICT_DATA_NAME_EXISTS);});
        long valueCount = this.count(Wrappers.lambdaQuery(SysDictData.class).eq(SysDictData::getType, data.getType()).eq(SysDictData::getValue, data.getValue()).notIn(SysDictData::getId, data.getId()));
        Assert.isFalse(valueCount > 0, () -> {throw new UserException(ResultEnum.DICT_DATA_VALUE_EXISTS);});
        this.updateById(data);
    }

    @Transactional(rollbackFor = Exception.class)
    public void remove(List<String> ids) {
        this.removeBatchByIds(ids);
    }

    public List<SysDictData> listByType(String type) {
        List<SysDictData> list = this.list(Wrappers.lambdaQuery(SysDictData.class).eq(SysDictData::getType, type));
        return list;
    }
}
