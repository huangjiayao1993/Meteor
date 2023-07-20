package cn.p2nn.meteor.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthorizeDto {

    @NotBlank(message = "ID不能为空")
    private String id;

    private List<String> authIds;

}
