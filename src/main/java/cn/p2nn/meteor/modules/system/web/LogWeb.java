package cn.p2nn.meteor.modules.system.web;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.p2nn.meteor.entity.SysLoginLog;
import cn.p2nn.meteor.entity.SysOperationLog;
import cn.p2nn.meteor.model.PageResult;
import cn.p2nn.meteor.model.Result;
import cn.p2nn.meteor.service.SysLoginLogService;
import cn.p2nn.meteor.service.SysOperationLogService;
import cn.p2nn.meteor.web.BaseWeb;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统日志控制器
 *
 * @author huangjiayao1993
 */
@Slf4j
@RestController
@RequestMapping("system/log")
@RequiredArgsConstructor
public class LogWeb extends BaseWeb {

    private final SysLoginLogService loginLogService;

    private final SysOperationLogService operationLogService;

    /**
     * 登录日志-分页列表
     *
     * @param page
     * @param log
     * @return
     */
    @SaCheckPermission("sys:log:login:list")
    @GetMapping("login/page")
    public Result loginPage(Page page, SysLoginLog log) {
        PageResult result = this.loginLogService.page(page, log);
        return Result.success(result);
    }

    /**
     * 操作日志-分页列表
     *
     * @param page
     * @param log
     * @return
     */
    @SaCheckPermission("sys:log:operation:list")
    @GetMapping("operation/page")
    public Result operationPage(Page page, SysOperationLog log) {
        PageResult result = this.operationLogService.page(page, log);
        return Result.success(result);
    }

}
