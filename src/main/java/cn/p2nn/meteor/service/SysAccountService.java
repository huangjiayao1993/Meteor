package cn.p2nn.meteor.service;

import cn.dev33.satoken.secure.BCrypt;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.RandomUtil;
import cn.p2nn.meteor.config.MeteorConfig;
import cn.p2nn.meteor.constants.CommonConstant;
import cn.p2nn.meteor.entity.SysAccount;
import cn.p2nn.meteor.enums.ResultEnum;
import cn.p2nn.meteor.exception.AuthException;
import cn.p2nn.meteor.exception.UserException;
import cn.p2nn.meteor.mapper.SysAccountMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SysAccountService extends ServiceImpl<SysAccountMapper, SysAccount> {

    private final MeteorConfig config;

    public SysAccount getByUsername(String username) {
        return this.lambdaQuery().eq(SysAccount::getUsername, username).one();
    }

    public SysAccount getByWxOpenid(String openid) {
        return this.lambdaQuery().eq(SysAccount::getWxOpenid, openid).one();
    }

    public static void checkPassword(String current, String storage) {
        boolean check = BCrypt.checkpw(current, storage);
        Assert.isTrue(check, () -> {throw new AuthException(ResultEnum.USERNAME_PASSWORD_ERROR);});
    }

    @Transactional(rollbackFor = Exception.class)
    public void resetPassword(List<String> ids) {
        List<SysAccount> list = this.listByIds(ids);
        list.forEach(item -> {
            String salt = BCrypt.gensalt();
            item.setSalt(salt).setPassword(BCrypt.hashpw(config.getDefaultPassword(), salt));
        });
        this.updateBatchById(list);
    }

    @Transactional(rollbackFor = Exception.class)
    public void changePassword(String accountId, String password, String newPassword) {
        SysAccount account = this.getById(accountId);
        Assert.notNull(account, () -> {throw new AuthException(ResultEnum.ACCOUNT_NOT_FOUND);});
        Assert.isTrue(BCrypt.checkpw(password, account.getPassword()), () -> {throw new UserException(ResultEnum.OLD_PASSWORD_ERROR);});
        String salt = BCrypt.gensalt();
        account.setSalt(salt).setPassword(BCrypt.hashpw(newPassword, salt));
        this.updateById(account);
    }

    @Transactional(rollbackFor = Exception.class)
    public SysAccount create(String username, String password) {
        SysAccount account = new SysAccount();
        String salt = BCrypt.gensalt();
        account.setUsername(username).setPassword(BCrypt.hashpw(password, salt)).setSalt(salt);
        this.save(account);
        return account;
    }

    @Transactional(rollbackFor = Exception.class)
    public SysAccount createWx(String openid) {
        SysAccount account = this.create(CommonConstant.WX_USERNAME_PREFIX + RandomUtil.randomString(24), config.getDefaultPassword());
        account.setWxOpenid(openid);
        this.updateById(account);
        return account;
    }
}
