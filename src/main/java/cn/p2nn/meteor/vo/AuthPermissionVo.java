package cn.p2nn.meteor.vo;

import java.io.Serializable;
import java.util.List;

import cn.p2nn.meteor.entity.SysUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 用户权限模型
 *
 * @author huangjiayao1993
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class AuthPermissionVo implements Serializable {

    private SysUser user;

    private List<String> roleList;

    private List<String> permissionList;
}
