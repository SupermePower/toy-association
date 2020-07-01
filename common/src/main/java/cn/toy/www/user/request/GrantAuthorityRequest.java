package cn.toy.www.user.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

/**
 * @Auther: zby
 * @Date: 18-11-1 11:08
 * @Description:
 */
@Getter@Setter@ToString
public class GrantAuthorityRequest {




    @ApiModelProperty(value = "权限点 和 功能映射对象", required = true)
    @NotNull(message = "权限点 和 功能映射对象")
    private List<AuthorityFunction> authorityFunctionList;


    @Getter@Setter@ToString
    public static class AuthorityFunction {
        @ApiModelProperty(value = "权限点id", required = true)
        @NotNull(message = "权限点id数组不能为空")
        private Set<String> authorityIds;

        @ApiModelProperty(value = "功能id", required = true)
        @NotNull(message = "功能id不能为空")
        private String functionId;
    }



}
