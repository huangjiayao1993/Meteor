package cn.p2nn.meteor.service;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.p2nn.meteor.entity.SysDictData;
import cn.p2nn.meteor.mapper.SysDictDataMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SysDictDataService extends ServiceImpl<SysDictDataMapper, SysDictData> {

}
