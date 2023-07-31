package cn.p2nn.meteor.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 图片验证码模型
 *
 * @author huangjiayao1993
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class CaptchaVo {

    private String uuid;

    private String image;

}
