package cn.p2nn.meteor.config;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.filter.SaServletFilter;
import cn.dev33.satoken.router.SaHttpMethod;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.json.JSONUtil;
import cn.p2nn.meteor.enums.ResultEnum;
import cn.p2nn.meteor.model.Result;
import cn.p2nn.meteor.resolver.PageArgumentResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final MeteorAuthConfig meteorAuthConfig;

    /**
     * 注册 [Sa-Token 全局过滤器]
     */
    @Bean
    public SaServletFilter getSaServletFilter() {
        return new SaServletFilter()
                // 指定 [拦截路由]
                .addInclude("/**")
                // 前置函数：在每次认证函数之前执行
                .setBeforeAuth(obj -> {
                    SaHolder.getResponse()
                            // ---------- 设置跨域响应头 ----------
                            // 允许指定域访问跨域资源
                            .setHeader("Access-Control-Allow-Origin", SaHolder.getRequest().getHeader("Origin"))
                            // 允许的请求方式
                            .setHeader("Access-Control-Allow-Methods", "PUT,DELETE,POST,GET")
                            // 允许的header参数
                            .setHeader("Access-Control-Allow-Headers", "AUTHORIZATION,CONTENT-TYPE")
                            // 允许凭证
                            .setHeader("Access-Control-Allow-Credentials", "true")
                            // 有效时间
                            .setHeader("Access-Control-Max-Age", "3600")
                    ;
                    // 如果是预检请求，则立即返回到前端
                    SaRouter.match(SaHttpMethod.OPTIONS).back();
                })
                // 认证函数: 每次请求执行
                .setAuth(obj -> {
                    SaRouter.notMatch(this.meteorAuthConfig.getIgnoreUrl()).check(() -> StpUtil.checkLogin());
                })
                // 认证异常后
                .setError(e -> JSONUtil.toJsonStr(Result.failed(ResultEnum.AUTH_FAILED)));
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new PageArgumentResolver());
    }

}
