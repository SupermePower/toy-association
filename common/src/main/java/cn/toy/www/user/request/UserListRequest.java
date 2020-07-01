package cn.toy.www.user.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;


/**
 * 类功能介绍
 *
 * @author: hsl
 * @date:2018-07-24 15:12
 */
@Getter@Setter
public class UserListRequest {

    /**
     * 职能区域
     */
    @ApiModelProperty(value = "职能区域", required = true)
    private String workPlace;

    /**
     * 人员名称
     */
    @ApiModelProperty(value = "人员名称")
    private String name;

    /**
     * 状态码
     */
    @ApiModelProperty(value = "状态码，0离职，1在职，999全部", required = true)
    private Integer status;

    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间", required = true)
    private Long startTime;

    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间", required = true)
    private Long endTime;

    /**
     * 页码
     */
    @ApiModelProperty(value = "页码", required = true)
    private Integer startPage;

    /**
     * 每页大小
     */
    @ApiModelProperty(value = "每页大小，全部传0", required = true)
    private Integer pageSize;

    /**
     * 筛选所属部门id
     */
    @ApiModelProperty(value = "筛选所属部门id")
    private String parentDepartmentId;
    /**
     * 筛选部门分组id
     */
    @ApiModelProperty(value = "筛选部门分组id")
    private String groupId;



    /**
     * 员工所在的部门
     */
    private Set<String> departmentIds;


    /**
     * 员工id
     */
    private String userId;


    /**
     * 员工id
     */
    private List<String> userIds;





    @Override
    public String toString() {
        return "UserListRequest{" +
                "workPlace='" + workPlace + '\'' +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", startPage=" + startPage +
                ", pageSize=" + pageSize +
                '}';
    }
}
