package cn.toy.www.request.received;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zjt
 * @description 已收管理请求参数
 * @date 2019/7/1 11:16
 */

@Setter
@Getter
@ToString
public class ReceivedRequest extends GeneralRequest{

    @ApiModelProperty(value = "支付方式 100标示微信支付200表示线下支付300表示系统自动支付")
    private Integer PayWay;
}
