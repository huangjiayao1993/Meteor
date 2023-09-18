package cn.p2nn.meteor.config;

import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.ListUtil;
import cn.p2nn.meteor.constants.CacheConstant;
import cn.p2nn.meteor.constants.CommonConstant;
import cn.p2nn.meteor.service.LoginTypeService;
import cn.p2nn.meteor.service.RedisService;
import cn.p2nn.meteor.service.SysMenuService;
import cn.p2nn.meteor.service.SysRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 认证权限
 *
 * @author huangjiayao1993
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class AuthorityConfig implements StpInterface {

    private final RedisService redisService;

    private final SysRoleService roleService;

    private final SysMenuService menuService;

    private final LoginTypeService loginTypeService;

    /**
     * 获取登录用户权限
     *
     * @param loginId
     * @param loginType
     * @return
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        log.info("获取权限getPermissionList, id = [{}]", loginId);
        AtomicReference<List<String>> list = new AtomicReference<>(ListUtil.empty());
        this.loginTypeService.switchType(loginType, Boolean.FALSE, (service) -> {
            String key = StringUtils.join(CacheConstant.PERMISSION_KEY, loginId);
            list.set(this.redisService.getList(key));
            if (list.get().isEmpty()) {
                list.set(this.menuService.listPermissionCode(loginId.toString()));
                if (!list.get().isEmpty()) {
                    this.redisService.setList(key, list.get());
                }
            }
            if (CommonConstant.ADMIN_ID.equals(StpUtil.getLoginId())) {
                list.get().add(CommonConstant.ADMIN_PERMISSION);
            }
        }, null);
        return list.get();
    }

    /**
     * 获取登录用户角色
     *
     * @param loginId
     * @param loginType
     * @return
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        log.info("获取权限getRoleList, id = [{}]", loginId);
        AtomicReference<List<String>> list = new AtomicReference<>(ListUtil.empty());
        this.loginTypeService.switchType(loginType, Boolean.FALSE, (service) -> {
            String key = StringUtils.join(CacheConstant.ROLE_KEY, loginId);
            list.set(this.redisService.getList(key));
            if (list.get().isEmpty()) {
                list.set(this.roleService.listRoleCode(loginId.toString()));
                if (!list.get().isEmpty()) {
                    this.redisService.setList(key, list.get());
                }
            }
        }, null);
        return list.get();
    }
}
