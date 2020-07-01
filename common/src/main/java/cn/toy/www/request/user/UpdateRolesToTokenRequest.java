package cn.toy.www.request.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * @program: xmxc
 * @description: 更新权限的接口请求参数
 * @author: Leo Chen
 * @Date: 2019-11-29 16:30
 **/
@Getter
@Setter
@ToString
public class UpdateRolesToTokenRequest {
    /**
     * 更新的权限
     */
    @NotNull
    private List<Map> roles;

    /**
     * 签名
     */
    @NotNull
    private String sign;
}
