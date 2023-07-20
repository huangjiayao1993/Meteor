package cn.p2nn.meteor.modules.system.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.p2nn.meteor.web.BaseWeb;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 系统字典控制器
 *
 * @author huangjiayao1993
 */
@Slf4j
@RestController
@RequestMapping("system/dict")
@RequiredArgsConstructor
public class DictWeb extends BaseWeb {

}
