package cn.p2nn.meteor.service;

import cn.p2nn.meteor.entity.SysOperationLog;
import cn.p2nn.meteor.mapper.SysOperationLogMapper;
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
public class SysOperationLogService extends ServiceImpl<SysOperationLogMapper, SysOperationLog> {

    public PageResult page(Page page, SysOperationLog log) {
        LambdaQueryWrapper<SysOperationLog> qw = Wrappers.lambdaQuery(SysOperationLog.class)
                .like(StringUtils.isNotBlank(log.getUsername()), SysOperationLog::getUsername, log.getUsername())
                .orderByDesc(SysOperationLog::getCreateTime);
        page = this.page(page, qw);
        return PageResult.parse(page);
    }

}
