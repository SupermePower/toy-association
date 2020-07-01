package cn.toy.www.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @description: 资源分配
 * @author: Zero
 * @date: 2020/4/12 下午3:40
 */
@Setter
@Getter
@ToString
public class ResourceDistributionRequest {
    @ApiModelProperty(value = "角色ID")
    @NotNull(message = "角色ID不能为空")
    private Long roleId;
    @ApiModelProperty(value = "资源ID")
    @NotEmpty(message = "资源ID不能唯恐")
    private List<Long> resourceIdList;
}
