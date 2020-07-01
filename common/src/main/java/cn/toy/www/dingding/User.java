package cn.toy.www.dingding;


import cn.toy.www.user.Role;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.List;

@Setter
@Getter
@ToString
public class User extends BaseModel {

    private String mobile;

    /**
     * 密码
     */
    private String password;

    /**
     * email 邮箱
     */
    private String email;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 钉钉企业 userId
     */
    private String userid;

    /**
     * 钉钉 职位
     */
    private String position;

    /**
     * 部门ids
     */
    private String department;



    /**
     * 负责区域ID
     */
    private String areaIds;

    private String name;
    private Integer revision;
    private Long createBy;
    private Long updateBy;
    private Byte delete;
    private Byte status;
    private Byte leader;
    private String jobnumber;
    private Byte admin;
    private Timestamp hiredDate;
    private Byte boss;

    /**
     * 用户角色
     */
    private List<Role> roles;

    /**
     * 管理平台：1-云豆本部；2-云豆律所
     */
    private Byte managementPlatform;
}
