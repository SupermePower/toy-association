package cn.toy.www.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @description: 转化率
 * @author: Zero
 * @date: 2020/6/14 下午5:06
 */
@Setter
@Getter
public class ConversionRatesVO {
    @ApiModelProperty(value = "和解/诉讼")
    private Integer settlementLitigationCount;
    @ApiModelProperty(value = "取证数量")
    private Integer obtainEvidenceCount;
    @ApiModelProperty(value = "疑似")
    private Integer suspectedCount;
}
