package cn.toy.www.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @description: 获取用户列表请求对象
 * @author: Zero
 * @date: 2020/4/12 下午6:28
 */
@Setter
@Getter
@ToString
public class QueryUserListRequest {
    @ApiModelProperty(value = "目标页数", required = true)
    @NotNull(message = "目标页数不能为空")
    private Integer startPage;
    @ApiModelProperty(value = "每页记录数", required = true)
    @NotNull(message = "每页记录数不能为空")
    private Integer pageSize;
    @ApiModelProperty(value = "姓名或手机号")
    private String param;
}
