package cn.toy.www.request.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @Auther: zby
 * @Date: 18-9-20 19:53
 * @Description:
 */
@Getter@Setter@ToString
public class ListUserRequest {





    /**
     * 状态码
     */
    @ApiModelProperty(value = "状态码，0离职，1在职，999全部", required = true)
    private Integer status;


    /**
     * 筛选是否具有某种权限点
     */
    @ApiModelProperty(value = "筛选是否具有某种权限点")
    private String authority;


    /**
     * 部门id 默认不查子部门  还没实现
     */
    @ApiModelProperty(value = "部门id 默认不查子部门  还没实现")
    private String departmentId;


    /**
     * 角色Id数组  查看具有角色之一的用户  默认不查子角色
     */
    @ApiModelProperty(value = "角色Id数组 查看具有角色之一的用户  默认不查子角色")
    private List<String>  hasRoles;


    /**
     * 部门递归查询 默认false 还没实现
     */
    @ApiModelProperty(value = "部门递归查询 默认false 还没实现")
    private Boolean departmentRecurse;


    /**
     * 角色递归查询 默认 false 还没实现
     */
    @ApiModelProperty(value = "角色递归查询 默认 false 还没实现")
    private Boolean roleRecurse;


    /**
     * 用户id 数组
     */
    @ApiModelProperty(value = "用户id数组")
    private List<String> userIds;
}
