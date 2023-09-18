package cn.p2nn.meteor.service;

import cn.hutool.core.util.StrUtil;
import cn.p2nn.meteor.entity.SysOperationLog;
import cn.p2nn.meteor.mapper.SysOperationLogMapper;
import cn.p2nn.meteor.model.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SysOperationLogService extends ServiceImpl<SysOperationLogMapper, SysOperationLog> {

    public PageResult page(Page page, SysOperationLog log) {
        page = this.lambdaQuery()
                .like(StrUtil.isNotBlank(log.getUsername()), SysOperationLog::getUsername, log.getUsername())
                .orderByDesc(SysOperationLog::getCreateTime)
                .page(page);
        return PageResult.parse(page);
    }

}
