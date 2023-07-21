package cn.p2nn.meteor.service;

import cn.p2nn.meteor.entity.SysLoginLog;
import cn.p2nn.meteor.mapper.SysLoginLogMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SysLoginLogService extends ServiceImpl<SysLoginLogMapper, SysLoginLog> {

}
