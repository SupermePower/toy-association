package cn.toy.www.user;


import cn.toy.www.AbstractBaseModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author: zhaobingyu
 * @Date: 4:27 PM 28/06/2018
 */
@Getter
@Setter
@ToString
public class Role extends AbstractBaseModel {


    /**
     * 角色名称
     */
    private String roleName;


    /**
     * 父角色id
     */
    private String parentId;


    /**
     * 是否删除 0 否 1 是
     */
    private Byte deleted;

    private Long createBy;
    private Long updateBy;
}
