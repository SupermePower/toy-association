package cn.toy.www.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @description: 分也请求对象
 * @author: Zero
 * @date: 2020/5/2 下午5:45
 */
@Setter
@Getter
@ToString
public class PageRequest {
    @ApiModelProperty(value = "当前页", required = true)
    @NotNull(message = "当前页不能为空")
    private Integer startPage;
    @ApiModelProperty(value = "每页记录数", required = true)
    @NotNull(message = "每页记录数不能为空")
    private Integer pageSize;
}
