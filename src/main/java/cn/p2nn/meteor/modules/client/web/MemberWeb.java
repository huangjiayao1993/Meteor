package cn.p2nn.meteor.modules.client.web;

import cn.p2nn.meteor.model.Result;
import cn.p2nn.meteor.web.BaseWeb;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 客户端会员控制器
 *
 * @author huangjiayao1993
 */
@Slf4j
@RestController
@RequestMapping("client/member")
@RequiredArgsConstructor
public class MemberWeb extends BaseWeb {

    @RequestMapping("test")
    public Object test() {
        return Result.success("123");
    }

}
