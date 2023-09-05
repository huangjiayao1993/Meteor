package cn.p2nn.meteor.service;

import cn.hutool.core.util.StrUtil;
import cn.p2nn.meteor.entity.SysLoginLog;
import cn.p2nn.meteor.mapper.SysLoginLogMapper;
import cn.p2nn.meteor.model.PageResult;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SysLoginLogService extends ServiceImpl<SysLoginLogMapper, SysLoginLog> {

    public PageResult page(Page page, SysLoginLog log) {
        LambdaQueryWrapper<SysLoginLog> qw = Wrappers.lambdaQuery(SysLoginLog.class)
                .like(StrUtil.isNotBlank(log.getUsername()), SysLoginLog::getUsername, log.getUsername())
                .orderByDesc(SysLoginLog::getCreateTime);
        page = this.page(page, qw);
        return PageResult.parse(page);
    }

}
