package cn.toy.www.user;


import cn.toy.www.AbstractBaseModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Getter
@Setter
public class SecurityUser extends AbstractBaseModel implements UserDetails {

    private String userName;

    private String mobile;

    private String password;

    private String name;

    /**
     * 角色id
     */
    private List<Role> roles;

    /**
     * 员工状态　０　离职　１　在职
     */
    private Byte status;

    /**
     * 权限点
     */
    private List<AuthorityPoint> authorities;


    public void setAuthorities(List<AuthorityPoint> authorities) {
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
