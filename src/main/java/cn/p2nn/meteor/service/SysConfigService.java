package cn.p2nn.meteor.service;

import cn.p2nn.meteor.entity.SysConfig;
import cn.p2nn.meteor.mapper.SysConfigMapper;
import cn.p2nn.meteor.model.PageResult;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SysConfigService extends ServiceImpl<SysConfigMapper, SysConfig> {

    public PageResult page(Page page, SysConfig config) {
        LambdaQueryWrapper<SysConfig> qw = Wrappers.lambdaQuery(SysConfig.class)
                .like(StringUtils.isNotBlank(config.getKey()), SysConfig::getKey, config.getKey())
                .orderByDesc(SysConfig::getId);
        page = this.page(page, qw);
        return PageResult.parse(page);
    }
}
