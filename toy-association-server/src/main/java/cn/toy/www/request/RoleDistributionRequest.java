package cn.toy.www.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @description: 角色分配
 * @author: Zero
 * @date: 2020/4/16 下午10:57
 */
@Setter
@Getter
@ToString
public class RoleDistributionRequest {
    @ApiModelProperty(value = "用户ID", required = true)
    @NotNull(message = "用户ID不能为空")
    private Long userId;
    @ApiModelProperty(value = "角色ID", required = true)
    @NotNull(message = "角色ID不能为空")
    private Long roleId;
}
