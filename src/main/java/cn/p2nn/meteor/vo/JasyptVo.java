package cn.p2nn.meteor.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class JasyptVo {

    /**
     * 原内容
     */
    private String content;

    /**
     * 加密后内容
     */
    private String encryptContent;

}
