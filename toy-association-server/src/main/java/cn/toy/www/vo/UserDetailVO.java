package cn.toy.www.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @description: 用户详情
 * @author: Zero
 * @date: 2020/4/16 下午11:06
 */
@Getter
@Setter
@ToString
public class UserDetailVO {
    @ApiModelProperty(value = "主键")
    private Long id;
    @ApiModelProperty(value = "姓名")
    private String name;
    @ApiModelProperty(value = "手机号")
    private String mobile;
    @ApiModelProperty(value = "头像")
    private String avatar;
}
