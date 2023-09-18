package cn.p2nn.meteor.web;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import cn.p2nn.meteor.dto.LoginDto;
import cn.p2nn.meteor.dto.WxLoginDto;
import cn.p2nn.meteor.entity.SysAccount;
import cn.p2nn.meteor.entity.SysUser;
import cn.p2nn.meteor.enums.ResultEnum;
import cn.p2nn.meteor.exception.AuthException;
import cn.p2nn.meteor.model.Result;
import cn.p2nn.meteor.modules.client.entity.CtMember;
import cn.p2nn.meteor.modules.client.service.CtMemberService;
import cn.p2nn.meteor.service.CaptchaService;
import cn.p2nn.meteor.service.LoginTypeService;
import cn.p2nn.meteor.service.SysAccountService;
import cn.p2nn.meteor.service.SysUserService;
import cn.p2nn.meteor.utils.StpClientUtil;
import cn.p2nn.meteor.vo.AuthPermissionVo;
import cn.p2nn.meteor.vo.CaptchaVo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 登录权限控制器
 *
 * @author huangjiayao1993
 */
@Slf4j
@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthWeb extends BaseWeb {

    private final SysAccountService accountService;

    private final SysUserService userService;

    private final CaptchaService captchaService;

    private final CtMemberService memberService;

    private final LoginTypeService loginTypeService;

    /**
     * 获取验证码
     *
     * @return
     */
    @GetMapping("captcha")
    public Result captcha() {
        CaptchaVo vo = this.captchaService.create();
        return Result.success(vo);
    }

    /**
     * 登录
     *
     * @param dto
     * @return
     */
    @PostMapping("login")
    public Result login(@RequestBody @Valid LoginDto dto) {
        SysAccount account = this.accountService.getByUsername(dto.getUsername());
        Assert.notNull(account, () -> {
            throw new AuthException(ResultEnum.USERNAME_PASSWORD_ERROR);
        });
        SysUser user = this.userService.getByAccountId(account.getId());
        Assert.notNull(user, () -> {
            throw new AuthException(ResultEnum.USERNAME_PASSWORD_ERROR);
        });
        SysAccountService.checkPassword(dto.getPassword(), account.getPassword());
        this.userService.checkLogin(user);
        StpUtil.login(user.getId());
        return Result.success(StpUtil.getTokenInfo());
    }

    /**
     * 微信授权码登录
     *
     * @param dto
     * @return
     */
    @PostMapping("wx/login")
    public Result wxLogin(@RequestBody @Valid WxLoginDto dto) throws WxErrorException {
        WxMaService wxMaService = new WxMaServiceImpl();
        // 获取openid
        Assert.isTrue(wxMaService.switchover(dto.getAppId()), () -> {
            throw new AuthException(ResultEnum.WX_CONFIG_NOT_FOUND);
        });
        WxMaJscode2SessionResult session = wxMaService.getUserService().getSessionInfo(dto.getCode());
        Assert.notEmpty(session.getOpenid(), () -> {
            throw new AuthException(ResultEnum.WX_OPENID_GET_ERROR);
        });
        // 查找账号
        SysAccount account = this.accountService.getByWxOpenid(session.getOpenid());
        if (ObjectUtil.isNull(account)) {
            account = this.accountService.createWx(session.getOpenid());
        }
        // 查找会员
        CtMember member = this.memberService.getByAccountId(account.getId());
        if (ObjectUtil.isNull(member)) {
            this.memberService.createWx(account.getId());
        }
        StpClientUtil.login(member.getId());
        return Result.success(StpClientUtil.getTokenInfo());
    }

    /**
     * 获取当前用户信息和权限
     *
     * @return
     */
    @GetMapping("current")
    public Result current(@RequestHeader String loginType) {
        AtomicReference<Object> data = new AtomicReference<>(new Object());
        AtomicReference<List<String>> roleList = new AtomicReference<>(ListUtil.empty());
        AtomicReference<List<String>> permissionList = new AtomicReference<>(ListUtil.empty());
        this.loginTypeService.switchType(loginType, Boolean.TRUE, (service) -> {
            Assert.isTrue(StpUtil.isLogin(), () -> {
                throw new AuthException(ResultEnum.AUTH_FAILED);
            });
            data.set(this.userService.getById(StpUtil.getTokenInfo().getLoginId().toString()));
            roleList.set(StpUtil.getRoleList());
            permissionList.set(StpUtil.getPermissionList());
        }, (service) -> {
            Assert.isTrue(StpClientUtil.isLogin(), () -> {
                throw new AuthException(ResultEnum.AUTH_FAILED);
            });
            data.set(this.memberService.getById(StpClientUtil.getTokenInfo().getLoginId().toString()));
        });
        AuthPermissionVo vo = new AuthPermissionVo(data.get(), roleList.get(), permissionList.get());
        return Result.success(vo);
    }

    /**
     * 注销
     *
     * @return
     */
    @DeleteMapping("logout")
    public Result logout(@RequestHeader String loginType) {
        this.loginTypeService.switchType(loginType, Boolean.FALSE, StpUtil::logout, StpClientUtil::logout);
        return Result.success();
    }
}
