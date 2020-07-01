package cn.toy.www.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @description: 用户下拉列表
 * @author: Zero
 * @date: 2020/4/16 下午9:12
 */
@Setter
@Getter
@ToString
public class UserSelectedListVO {
    @ApiModelProperty(value = "用户主键")
    private Long id;
    @ApiModelProperty(value = "姓名")
    private String name;
}
