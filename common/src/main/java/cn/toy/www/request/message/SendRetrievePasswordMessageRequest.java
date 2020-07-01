package cn.toy.www.request.message;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @description 发送找回密码短信请求对象
 * @author: fly
 * @Date: 2018/7/19 下午3:24
 */
@Setter
@Getter
@ToString
public class SendRetrievePasswordMessageRequest {
    @ApiModelProperty(value = "手机号")
    @NotNull(message = "手机号不能为空")
    private String tel;
    @ApiModelProperty(value = "密码")
    @NotNull(message = "密码不能为空")
    private String passWord;
}