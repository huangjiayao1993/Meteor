package cn.p2nn.meteor.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginDto {

    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;

    @NotBlank(message = "登录方式不能为空")
    private String loginType;

    @NotBlank(message = "验证码ID不能为空")
    private String uuid;

    @NotBlank(message = "验证码不能为空")
    private String code;
}
