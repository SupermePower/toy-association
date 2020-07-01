package cn.toy.www.request;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class LoginRequest {

    @NotBlank(message = "手机号码不能为null")
    @ApiModelProperty(value = "手机号码", required = true)
    private String mobile;

    @NotBlank(message = "密码不能为null")
    @ApiModelProperty(value = "密码", required = true)
    private String password;
}
