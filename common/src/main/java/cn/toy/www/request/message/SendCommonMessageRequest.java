package cn.toy.www.request.message;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author fly
 * @description 发送流程短信息请求对象
 * @date 2019/4/29 15:52
 */
@Setter
@Getter
@ToString
@Accessors(chain=true)
public class SendCommonMessageRequest {
    @ApiModelProperty(value = "手机号")
    @NotNull(message = "手机号不能为空")
    private Long tel;

    @ApiModelProperty(value = "参数内容")
    @NotNull(message = "参数内容")
    private List<String> message;

    @ApiModelProperty(value = "短信模板ID")
    @NotNull(message = "短信模板ID")
    private String tmpId;
}
