package cn.toy.www.request.message;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @author fly
 * @description 发送流程短信息请求对象
 * @date 2019/4/29 15:52
 */
@Setter
@Getter
@ToString
public class SendProcessMessageRequest {
    @ApiModelProperty(value = "手机号")
    @NotNull(message = "手机号不能为空")
    private String tel;
    @ApiModelProperty(value = "消息内容")
    @NotNull(message = "内容不能为空")
    private String message;
}
