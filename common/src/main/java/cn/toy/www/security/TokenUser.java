package cn.toy.www.security;

import cn.toy.www.AbstractBaseModel;
import cn.toy.www.user.Role;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.TreeSet;

/**
 * @author: zhaobingyu
 * @Date: 2:51 PM 04/07/2018
 */
@Getter
@Setter
@ToString(callSuper = true)
public class TokenUser extends AbstractBaseModel {


    private String name;


    private String mobile;

    private String userName;


    private List<Role> roles;


    private List<String> authorities;


    private TreeSet<Integer> workPlaceIds;


    private List<String> scope;


    private Long exp;


    private String jti;

    private Long loginTime;
}
