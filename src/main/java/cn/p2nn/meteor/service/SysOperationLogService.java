package cn.p2nn.meteor.service;

import cn.p2nn.meteor.entity.SysOperationLog;
import cn.p2nn.meteor.mapper.SysOperationLogMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SysOperationLogService extends ServiceImpl<SysOperationLogMapper, SysOperationLog> {

}
