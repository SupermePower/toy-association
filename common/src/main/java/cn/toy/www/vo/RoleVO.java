package cn.toy.www.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author: fly
 * @Description: 角色
 * @Date: 2020/4/6
 */
@Setter
@Getter
@ToString
public class RoleVO {
    @ApiModelProperty(value = "角色主键")
    private String id;
    @ApiModelProperty(value = "角色名称")
    private String roleName;
}
