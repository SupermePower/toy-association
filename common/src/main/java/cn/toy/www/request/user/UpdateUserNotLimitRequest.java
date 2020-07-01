package cn.toy.www.request.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

/**
 * @description 修改用户信息请求对象
 * @author: fly
 * @Date: 2018/7/25 下午6:16
 */
@Setter
@Getter
@ToString
public class UpdateUserNotLimitRequest {

    @ApiModelProperty(value = "用户主键")
    @NotNull(message = "用户主键")
    private String userId;
    @ApiModelProperty(value = "姓名")
    @NotNull(message = "姓名不能为空")
    private String name;
    @ApiModelProperty(value = "电话")
    @Size(min = 11, max = 11, message = "手机号长度不对")
    private String tel;
    @ApiModelProperty(value = "部门")
    private String department;
    @ApiModelProperty(value = "职位")
    private String position;
    @ApiModelProperty(value = "职能区域")
    private String workPlaces;
    @ApiModelProperty(value = "状态：0-离职；1-在职")
    private Byte status;
    @ApiModelProperty(value = "员工编号")
    private String jobnumber;
    @ApiModelProperty(value = "入职时间")
    private Timestamp hiredDate;
    @ApiModelProperty(value = "是否是 部门leader 具有部门审批权限   0 否 1 是")
    private Byte leader;
    @ApiModelProperty(value = "头像")
    private String avatar;
    @ApiModelProperty(value = "角色主键")
    private Set<String> roleIds;
    @ApiModelProperty(value = "email")
    private String email;

    @ApiModelProperty(value = "是否具有小程序登录权限 0 否 1 是")
    @Max(value = 1)
    @Min(value = 0)
    private Byte authApp;

    @ApiModelProperty(value = "数据权限部门id")
    private List<String> dataDepartmentId;


}
