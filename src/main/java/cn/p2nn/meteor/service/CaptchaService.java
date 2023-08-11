package cn.p2nn.meteor.service;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.codec.Base64Encoder;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.IdUtil;
import cn.p2nn.meteor.config.MeteorConfig;
import cn.p2nn.meteor.constants.CacheConstant;
import cn.p2nn.meteor.constants.CommonConstant;
import cn.p2nn.meteor.enums.ResultEnum;
import cn.p2nn.meteor.exception.BusinessException;
import cn.p2nn.meteor.vo.CaptchaVo;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class CaptchaService {

    private final MeteorConfig config;

    private final RedisService redisService;

    @SneakyThrows
    public CaptchaVo create() {
        String uuid = IdUtil.fastUUID();
        LineCaptcha captcha = CaptchaUtil.createLineCaptcha(500, 200, 4, 100);
        captcha.setBackground(Color.getHSBColor(221, 221, 221));
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(captcha.getImage(), "png", os);
        log.error("获取验证码：uuid = [{}], code = [{}]", uuid, captcha.getCode());
        this.redisService.set(uuid, captcha.getCode(), config.getCaptchaTimeout(), TimeUnit.MINUTES);
        String image = StringUtils.join(CommonConstant.CAPTCHA_BASE64_PREFIX, Base64Encoder.encode(os.toByteArray()));
        return new CaptchaVo(uuid, image);
    }

    public boolean valid(String uuid, String code) {
        String open = this.redisService.get(StringUtils.join(CacheConstant.CONFIG_KEY, CacheConstant.OPEN_CAPTCHA_KEY));
        if (!BooleanUtil.toBoolean(open)) {
            return true;
        }
        String cache = this.redisService.get(uuid);
        Assert.notNull(cache, () -> {
            throw new BusinessException(ResultEnum.CAPTCHA_EXPIRE_TIMEOUT);
        });
        boolean match = StringUtils.equalsIgnoreCase(cache, code);
        Assert.isTrue(match, () -> {
            throw new BusinessException(ResultEnum.CAPTCHA_VALID_ERROR);
        });
        return match;
    }
}
