package cn.p2nn.meteor.config;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpUtil;
import cn.p2nn.meteor.constants.CacheConstant;
import cn.p2nn.meteor.constants.CommonConstant;
import cn.p2nn.meteor.service.RedisService;
import cn.p2nn.meteor.service.SysMenuService;
import cn.p2nn.meteor.service.SysRoleService;
import lombok.RequiredArgsConstructor;

/**
 * 认证权限
 *
 * @author huangjiayao1993
 */
@Component
@RequiredArgsConstructor
public class AuthorityConfig implements StpInterface {

    private final RedisService redisService;

    private final SysRoleService roleService;

    private final SysMenuService menuService;

    /**
     * 获取登录用户权限
     *
     * @param id
     * @param device
     * @return
     */
    @Override
    public List<String> getPermissionList(Object id, String device) {
        String key = StringUtils.join(CacheConstant.PERMISSION_KEY, id);
        List<String> cacheList = this.redisService.getList(key);
        List<String> list = new ArrayList<>();
        if (cacheList.isEmpty()) {
            list = this.menuService.listPermissionCode(id.toString());
            if (!list.isEmpty()) {
                this.redisService.setList(key, list);
            }
        }
        if (CommonConstant.ADMIN_ID.equals(StpUtil.getLoginId())) {
            list.add(CommonConstant.ADMIN_PERMISSION);
        }
        return list;
    }

    /**
     * 获取登录用户角色
     *
     * @param id
     * @param device
     * @return
     */
    @Override
    public List<String> getRoleList(Object id, String device) {
        String key = StringUtils.join(CacheConstant.ROLE_KEY, id);
        List<String> cacheList = this.redisService.getList(key);
        List<String> list = new ArrayList<>();
        if (cacheList.isEmpty()) {
            list = this.roleService.listRoleCode(id.toString());
            if (!list.isEmpty()) {
                this.redisService.setList(key, list);
            }
        }
        return list;
    }
}
