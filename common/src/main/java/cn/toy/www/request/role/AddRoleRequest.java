package cn.toy.www.request.role;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * @Author: fly
 * @Description: 新增角色请求对象
 * @Date： 2020/4/6
 */
@Setter
@Getter
@ToString
public class AddRoleRequest {
    @ApiModelProperty(value = "角色名称", required = true)
    @NotBlank(message = "角色名称不能为空")
    private String roleName;
}
