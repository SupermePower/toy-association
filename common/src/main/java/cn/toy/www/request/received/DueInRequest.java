package cn.toy.www.request.received;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zjt
 * @description 待收管理请求参数
 * @date 2019/7/2 10:42
 */
@Setter
@Getter
@ToString
public class DueInRequest extends GeneralRequest{

    @ApiModelProperty(value = "付款状态 0:未缴费 1:待对账",notes = "0:未缴费 1:待对账")
    private Integer applyStatus;
}
