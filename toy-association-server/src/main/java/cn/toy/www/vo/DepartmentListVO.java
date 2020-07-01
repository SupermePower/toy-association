package cn.toy.www.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @description: 部门列表
 * @author: Zero
 * @date: 2020/4/12 下午6:34
 */
@Getter
@Setter
@ToString
public class DepartmentListVO {
    @ApiModelProperty(value = "部门ID")
    private Long departmentId;
    @ApiModelProperty(value = "部门名称")
    private String departmentName;
}
