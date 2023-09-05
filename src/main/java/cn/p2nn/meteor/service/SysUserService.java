package cn.p2nn.meteor.service;

import java.util.List;

import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.dev33.satoken.secure.BCrypt;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.lang.Assert;
import cn.p2nn.meteor.config.MeteorConfig;
import cn.p2nn.meteor.constants.FieldConstant;
import cn.p2nn.meteor.entity.BaseEntity;
import cn.p2nn.meteor.entity.SysUser;
import cn.p2nn.meteor.entity.SysUserRole;
import cn.p2nn.meteor.enums.ResultEnum;
import cn.p2nn.meteor.exception.AuthException;
import cn.p2nn.meteor.exception.UserException;
import cn.p2nn.meteor.mapper.SysUserMapper;
import cn.p2nn.meteor.model.PageResult;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SysUserService extends ServiceImpl<SysUserMapper, SysUser> {

    private final SysUserRoleService userRoleService;

    private final MeteorConfig config;

    public PageResult page(Page page, SysUser user) {
        LambdaQueryWrapper<SysUser> qw = Wrappers.lambdaQuery(SysUser.class)
                .select(SysUser.class, i -> !(i.getProperty().equals(FieldConstant.Fields.password)) && !(i.getProperty().equals(FieldConstant.Fields.salt)))
                .like(StrUtil.isNotBlank(user.getNickname()), SysUser::getNickname, user.getNickname())
                .eq(StrUtil.isNotBlank(user.getOrgId()), SysUser::getOrgId, user.getOrgId())
                .orderByDesc(BaseEntity::getCreateTime);
        page = this.page(page, qw);
        return PageResult.parse(page);
    }

    @Transactional(rollbackFor = Exception.class)
    public void create(SysUser user) {
        long usernameCount = this.count(Wrappers.lambdaQuery(SysUser.class).eq(SysUser::getUsername, user.getUsername()));
        Assert.isFalse(usernameCount > 0, () -> {throw new UserException(ResultEnum.USERNAME_EXISTS);});
        long mobileCount = this.count(Wrappers.lambdaQuery(SysUser.class).eq(SysUser::getMobile, user.getMobile()));
        Assert.isFalse(mobileCount > 0, () -> {throw new UserException(ResultEnum.MOBILE_EXISTS);});
        String salt = BCrypt.gensalt();
        user.setSalt(salt).setPassword(BCrypt.hashpw(user.getPassword(), salt));
        this.save(user);
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(SysUser user) {
        long nicknameCount = this.count(Wrappers.lambdaQuery(SysUser.class).eq(SysUser::getNickname, user.getNickname()).notIn(BaseEntity::getId, user.getId()));
        Assert.isFalse(nicknameCount > 0, () -> {throw new UserException(ResultEnum.NICKNAME_EXISTS);});
        long mobileCount = this.count(Wrappers.lambdaQuery(SysUser.class).eq(SysUser::getMobile, user.getMobile()).notIn(BaseEntity::getId, user.getId()));
        Assert.isFalse(mobileCount > 0, () -> {throw new UserException(ResultEnum.MOBILE_EXISTS);});
        this.updateById(user);
    }

    @Transactional(rollbackFor = Exception.class)
    public void remove(List<String> ids) {
        this.removeBatchByIds(ids);
        this.userRoleService.remove(Wrappers.lambdaQuery(SysUserRole.class).in(SysUserRole::getUserId, ids));
    }

    @Transactional(rollbackFor = Exception.class)
    public void resetPassword(List<String> ids) {
        List<SysUser> list = this.listByIds(ids);
        list.stream().forEach(item -> {
            String salt = BCrypt.gensalt();
            item.setSalt(salt).setPassword(BCrypt.hashpw(config.getDefaultPassword(), salt));
        });
        this.updateBatchById(list);
    }

    @Transactional(rollbackFor = Exception.class)
    public void changePassword(String id, String password, String newPassword) {
        SysUser user = this.getById(id);
        Assert.isTrue(BCrypt.checkpw(password, user.getPassword()), () -> {throw new UserException(ResultEnum.OLD_PASSWORD_ERROR);});
        String salt = BCrypt.gensalt();
        user.setSalt(salt).setPassword(BCrypt.hashpw(newPassword, salt));
        this.updateById(user);
    }

    public SysUser getByUsername(String username) {
        return this.getOne(Wrappers.lambdaQuery(SysUser.class).eq(SysUser::getUsername, username));
    }

    public SysUser getByMobile(String mobile) {
        return this.getByUsername(mobile);
    }

    public static void checkPassword(String current, String storage) {
        boolean check = BCrypt.checkpw(current, storage);
        Assert.isTrue(check, () -> {throw new AuthException(ResultEnum.USERNAME_PASSWORD_ERROR);});
    }

    public void checkLogin(SysUser user) {
        Assert.notNull(user, () -> {throw new AuthException(ResultEnum.USER_NOT_FOUND);});
        Assert.isFalse(user.isStatus(), () -> {throw new UserException(ResultEnum.USER_DEACTIVATED);});
        if (user.isEnableUsed()) {
            boolean inTime = LocalDateTimeUtil.isIn(LocalDateTimeUtil.now(), user.getEnableStartTime(), user.getEnableEndTime());
            Assert.isTrue(inTime, () -> {throw new UserException(ResultEnum.USER_NOTIN_USE_TIME);});
        }
    }

}