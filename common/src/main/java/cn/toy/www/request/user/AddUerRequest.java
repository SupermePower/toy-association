package cn.toy.www.request.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * @description 操作用户请求对象
 * @author: fly
 * @Date: 2018/7/18 下午12:23
 */
@Setter
@Getter
@ToString
public class AddUerRequest {
    @ApiModelProperty(value = "姓名")
    @NotBlank(message = "姓名不能为空")
    private String name;
    @ApiModelProperty(value = "电话")
    @Size(min = 11, max = 11, message = "手机号长度不对")
    private String mobile;
    @ApiModelProperty(value = "用户名")
    private String userName;
    @ApiModelProperty(value = "职位")
    @NotBlank(message = "职位不能为空")
    private String position;
    @ApiModelProperty(value = "状态：0-离职；1-在职")
    private Byte status;
    @ApiModelProperty(value = "员工编号")
    @NotBlank(message = "员工编号不能为空")
    private String jobnumber;
    @ApiModelProperty(value = "入职时间")
    private Timestamp hiredDate;
    @ApiModelProperty(value = "是否是 部门leader 具有部门审批权限  0 否 1 是")
    private Byte leader;
    @ApiModelProperty(value = "头像")
    private String avatar;
    @ApiModelProperty(value = "角色主键")
    @NotEmpty(message = "角色不能为空")
    private List<String> roleIds;
    @ApiModelProperty(value = "email")
    @NotBlank(message = "email不能为空")
    private String email;
}
