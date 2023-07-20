package cn.p2nn.meteor.service;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.p2nn.meteor.entity.SysDict;
import cn.p2nn.meteor.mapper.SysDictMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SysDictService extends ServiceImpl<SysDictMapper, SysDict> {

}
