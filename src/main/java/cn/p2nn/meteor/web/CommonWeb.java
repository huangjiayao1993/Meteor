package cn.p2nn.meteor.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.p2nn.meteor.model.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 常规控制器
 *
 * @author huangjiayao1993
 */
@Slf4j
@RestController
@RequestMapping("common")
@RequiredArgsConstructor
public class CommonWeb extends BaseWeb {

    /**
     * 上传
     *
     * @return
     */
    @PostMapping("upload")
    public Result upload() {
        return Result.success();
    }

}
