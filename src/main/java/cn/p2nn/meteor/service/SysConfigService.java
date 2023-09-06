package cn.p2nn.meteor.service;

import cn.hutool.core.util.StrUtil;
import cn.p2nn.meteor.constants.CacheConstant;
import cn.p2nn.meteor.entity.SysConfig;
import cn.p2nn.meteor.mapper.SysConfigMapper;
import cn.p2nn.meteor.model.PageResult;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SysConfigService extends ServiceImpl<SysConfigMapper, SysConfig> {

    private final RedisService redisService;

    @PostConstruct
    public void init() {
        List<SysConfig> list = this.list();
        list.stream().forEach(item -> {
            this.redisService.set(StringUtils.join(CacheConstant.CONFIG_KEY, item.getKey()), item.getValue());
        });
    }

    public void refresh() {
        this.clean();
        this.init();
    }

    public void clean() {
        this.redisService.delete(StringUtils.join(CacheConstant.CONFIG_KEY, "*"));
    }

    public PageResult page(Page page, SysConfig config) {
        LambdaQueryWrapper<SysConfig> qw = Wrappers.lambdaQuery(SysConfig.class)
                .like(StrUtil.isNotBlank(config.getKey()), SysConfig::getKey, config.getKey())
                .orderByDesc(SysConfig::getId);
        page = this.page(page, qw);
        return PageResult.parse(page);
    }
}
