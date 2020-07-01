package cn.toy.www.request.mail;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author fly
 * @description 发送系统异常邮件请求对象
 * @date 2019/3/28 11:34
 */
@Data
public class SendSystemExceptionMailRequest implements Serializable {
    @ApiModelProperty(value = "邮件标题")
    @NotNull(message = "标题不能为空")
    private String mailTitle;
    @ApiModelProperty(value = "邮件内容")
    @NotNull(message = "内容不能为空")
    private String mailContent;
    @ApiModelProperty(value = "发送类型：1-系统异常；2-电销控制台消息补全驳回")
    @NotNull(message = "发送类型不能为空")
    private Byte source;
}
