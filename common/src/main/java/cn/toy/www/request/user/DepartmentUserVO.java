package cn.toy.www.request.user;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 * @Author zby
 * @Description:
 * @Date: 下午2:42 2018/7/23
 */
@Getter@Setter
public class DepartmentUserVO {


    /**
     * id
     */
    private String id;

    /**
     * 用户ID(接口查询员工信息的时候使用)
     */
    private String userId;

    /**
     * 姓名
     */
    private String name;


    /**
     * 是否是部门leader  0  否 1 是
     */
    private Byte leader;


    /**
     * 电话
     */
    private String tel;


    /**
     * 头像
     */
    private String avatar;



    /**状态：0-离职；1-在职*/
    private Byte status;

    /**
     * 角色ID
     */
    private String roleId;

    /**
     * 职能区域
     */
    private Integer workPlace;
    /**
     * 职能区域名称
     */
    private String areaName;
    /**
     * 部门id
     */
    private String departmentName;

    /**
     * 部门
     */
    @ApiModelProperty(value = "部门")
    private String department;

    /**
     * 职位名称
     */
    @ApiModelProperty(value = "职位名称")
    private String positionName;

    /**
     * 角色名
     */
    private String roleName;

    /**
     * 执行第几步审核
     */
    private int processLevel;

    @Override
    public String toString() {
        return "DepartmentUserVO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        DepartmentUserVO that = (DepartmentUserVO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(name, that.name) &&
                Objects.equals(leader, that.leader) &&
                Objects.equals(tel, that.tel) &&
                Objects.equals(avatar, that.avatar) &&
                Objects.equals(status, that.status) &&
                Objects.equals(workPlace, that.workPlace) &&
                Objects.equals(roleId, that.roleId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, userId, name, leader, tel, avatar, status, roleId,workPlace);
    }
}
