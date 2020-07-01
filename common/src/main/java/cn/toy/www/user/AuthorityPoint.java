package cn.toy.www.user;

import cn.toy.www.AbstractBaseModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

/**
 * @Auther: zby
 * @Date: 18-10-31 18:41
 * @Description:  接口权限点
 */
@Getter
@Setter
public class AuthorityPoint extends AbstractBaseModel implements GrantedAuthority {


    /**
     * id
     */
    private Long id;



    /**
     * 权限点编码
     */
    private String code;


    /**
     * 权限点名称
     */
    private String authorityName;


    /**
     * 权限类型
     */
    private String type;


    @Override
    public String getAuthority() {
        return code;
    }
}
