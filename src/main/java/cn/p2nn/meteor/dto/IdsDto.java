package cn.p2nn.meteor.dto;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class IdsDto {

    @NotNull(message = "编号不能为空")
    private List<String> ids;

}
