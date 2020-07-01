package cn.toy.www.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.List;

/**
 * @description: 用户列表
 * @author: GOD
 * @date: 2020/4/12 下午6:32
 */
@Setter
@Getter
@ToString
public class UserListVO {
    @ApiModelProperty(value = "主键")
    private Long id;
    @ApiModelProperty(value = "姓名")
    private String name;
    @ApiModelProperty(value = "职位")
    private String position;
    @ApiModelProperty(value = "手机号")
    private String mobile;
    @ApiModelProperty(value = "入职时间")
    private Timestamp hiredDate;
    @ApiModelProperty(value = "邮箱")
    private String email;
    @ApiModelProperty(value = "状态：0-离职；1-在职")
    private Byte status;
    @ApiModelProperty(value = "角色名称")
    private String roleName;
    @ApiModelProperty(value = "部门列表")
    private List<DepartmentListVO> departmentList;
    @ApiModelProperty(value = "部门", hidden = true)
    private String department;
    @ApiModelProperty(value = "角色ID")
    private Long roleId;
    @ApiModelProperty(value = "头像")
    private String avatar;
}
