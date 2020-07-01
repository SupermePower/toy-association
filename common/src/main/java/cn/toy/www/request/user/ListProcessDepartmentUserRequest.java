package cn.toy.www.request.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Auther: zby
 * @Date: 18-11-20 16:30
 * @Description:
 */
@Getter@Setter@ToString
public class ListProcessDepartmentUserRequest {


    /**
     * 查看小组leader
     */
    private boolean groupLeader;


    /**
     * 查看 部门leader
     */
    private boolean departmentLeader;


    /**
     * 递归查看所有部门leader 直到 公司leader
     */
    private boolean companyLeaderRecurse;




}
