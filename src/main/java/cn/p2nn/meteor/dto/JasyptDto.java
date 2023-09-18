package cn.p2nn.meteor.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class JasyptDto {

    @NotNull(message = "内容不能为空")
    private String content;

}
