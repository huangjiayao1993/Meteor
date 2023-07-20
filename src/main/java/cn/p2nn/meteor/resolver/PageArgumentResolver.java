package cn.p2nn.meteor.resolver;

import java.util.Enumeration;
import java.util.HashMap;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.hutool.core.map.MapUtil;
import cn.p2nn.meteor.constants.FieldConstant;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PageArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType().equals(Page.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) {
        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        Enumeration<String> parameterNames = request.getParameterNames();
        HashMap<String, Object> params = MapUtil.newHashMap();
        while (parameterNames.hasMoreElements()) {
            String key = parameterNames.nextElement();
            params.put(key, request.getParameter(key));
        }
        Long current = MapUtil.getLong(params, FieldConstant.Fields.current, 1L);
        Long size = MapUtil.getLong(params, FieldConstant.Fields.pageSize, 10L);
        Page page = new Page(current, size);
        return page;
    }

}
