package cn.toy.www.request.message;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author 高德魁
 * @date 2019/9/19 11:04
 * @className StoreManagerServerCodeRequest
 * @description TODO
 **/
@Setter
@Getter
public class StoreManagerServerCodeRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "手机号")
    private String tel;

    @ApiModelProperty(value = "项目Id")
    private Long projectId;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "账单时间")
    private String billDate;

    @ApiModelProperty(value = "验证码")
    private String code;

}
