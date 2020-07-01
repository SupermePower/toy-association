package cn.toy.www.request.received;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Auther: zjt
 * @Date: 2019/7/4 20:29
 * @Description:
 */

@Setter
@Getter
@ToString
public class GeneralRequest {
    @ApiModelProperty(value = "城市id")
    private Long CityId;
    @ApiModelProperty(value = "项目名称")
    private String projectName;
    @ApiModelProperty(value = "商户名称")
    private String merchantName;
    @ApiModelProperty(value = "付款时间 yyyy-MM-dd(日期) yyyy-MM（月份）")
    private String applyDate;
    @ApiModelProperty(value = "每页记录数")
    private Integer pageSize;
    @ApiModelProperty(value = "开始页")
    private Integer startPage;
}
