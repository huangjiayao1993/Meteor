package cn.p2nn.meteor.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * app应用信息
 *
 * @author huangjiayao1993
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class AppInfoVo {

    private String version;

}
