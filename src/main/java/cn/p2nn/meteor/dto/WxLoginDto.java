package cn.p2nn.meteor.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class WxLoginDto {

    @NotBlank(message = "appId不能为空")
    private String appId;

    @NotBlank(message = "授权码不能为空")
    private String code;
}
