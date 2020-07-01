package cn.toy.www.request.role;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @description: 修改角色
 * @author: Zero
 * @date: 2020/4/16 下午9:31
 */
@Setter
@Getter
public class UpdateRoleRequest {
    @ApiModelProperty(value = "角色ID")
    @NotNull(message = "角色ID不能为空")
    private Long id ;
    @ApiModelProperty(value = "角色名称")
    @NotBlank(message = "角色名称不能为空")
    private String roleName;
}
