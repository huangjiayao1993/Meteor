package cn.p2nn.meteor.web;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestController;

import cn.hutool.core.date.DatePattern;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 基类控制器
 *
 * @author huangjiayao1993
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public abstract class BaseWeb {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(LocalDateTime.class, new CustomDateEditor(new SimpleDateFormat(DatePattern.NORM_DATETIME_PATTERN), Boolean.TRUE));
    }

}
