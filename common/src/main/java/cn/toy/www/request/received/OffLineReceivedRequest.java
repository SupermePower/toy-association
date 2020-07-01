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
public class OffLineReceivedRequest extends GeneralRequest{

   @ApiModelProperty(value = "银行账号")
    private String cardNumber;
}
