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
public class UpdateUserRequest {
    @ApiModelProperty(value = "用户主键")
    @NotNull(message = "用户主键")
    private String id;
    @ApiModelProperty(value = "姓名")
    @NotNull(message = "姓名不能为空")
    private String name;
    @ApiModelProperty(value = "电话")
    @NotNull(message = "电话不能为空")
    @Size(min = 11, max = 11, message = "手机号长度不对")
    private String tel;
    @ApiModelProperty(value = "部门")
    @NotNull(message = "部门不能为空")
    private String department;
    @ApiModelProperty(value = "职位")
    @NotNull(message = "职位不能为空")
    private String position;
    @ApiModelProperty(value = "头像")
    private String avatar;
    @ApiModelProperty(value = "角色主键")
    @NotNull(message = "角色不能为空")
    private Set<String> roleIds;
    @ApiModelProperty(value = "email")
    private String email;
}
