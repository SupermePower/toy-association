package cn.toy.www.uaa.security;


import cn.toy.www.uaa.service.CustomerUserDetailServiceImpl;
import cn.toy.www.user.SecurityUser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 自定义CustomerAccessTokenConverter 这个类的作用主要用于AccessToken的转换，
 * 默认使用DefaultAccessTokenConverter 这个装换器
 * DefaultAccessTokenConverter有个UserAuthenticationConverter，这个转换器作用是把用户的信息放入token中，
 * 默认只是放入username
 * <p>
 * 自定义了下这个方法，加入了额外的信息
 * <p>
 *
 * @author Created by fly on 2018/7/4.
 *         2018-07-04 9:54
 */
public class CustomerAccessTokenConverter extends DefaultAccessTokenConverter {


    public CustomerAccessTokenConverter(CustomerUserDetailServiceImpl customUserDetailsService) {
        super.setUserTokenConverter(new CustomerUserAuthenticationConverter(customUserDetailsService));
    }


    private class CustomerUserAuthenticationConverter extends DefaultUserAuthenticationConverter {
        private CustomerUserDetailServiceImpl customerUserDetailService;

        public CustomerUserAuthenticationConverter(CustomerUserDetailServiceImpl customerUserDetailService) {
            this.customerUserDetailService = customerUserDetailService;
        }

        @Override
        public Map<String, ?> convertUserAuthentication(Authentication authentication) {
            Map<String, Object> response = new LinkedHashMap<>();
            response.put("id", ((SecurityUser) authentication.getPrincipal()).getId());
            response.put("name", ((SecurityUser) authentication.getPrincipal()).getName());
            response.put("mobile", ((SecurityUser) authentication.getPrincipal()).getMobile());
            response.put("userName", ((SecurityUser) authentication.getPrincipal()).getUsername());
            response.put("roles", ((SecurityUser) authentication.getPrincipal()).getRoles());
            if (authentication.getAuthorities() != null && !authentication.getAuthorities().isEmpty()) {
                response.put("authorities", AuthorityUtils.authorityListToSet(authentication.getAuthorities()));
            }
            return response;
        }

        @Override
        public Authentication extractAuthentication(Map<String, ?> map) {
            if (map.containsKey("id")) {
                String userId = map.get("id").toString();

                Object principal = null;
                Collection<? extends GrantedAuthority> authorities = this.getAuthorities(map);
                if (this.customerUserDetailService != null) {
                    UserDetails user = this.customerUserDetailService.loadUserByUserId(userId);
                    authorities = user.getAuthorities();
                    principal = user;
                }
                return new UsernamePasswordAuthenticationToken(principal, "N/A", authorities);
            } else {
                return null;
            }
        }

        private Collection<? extends GrantedAuthority> getAuthorities(Map<String, ?> map) {
            if (!map.containsKey("authorities")) {
                return null;
            } else {
                Object authorities = map.get("authorities");
                if (authorities instanceof String) {
                    return AuthorityUtils.commaSeparatedStringToAuthorityList((String) authorities);
                } else if (authorities instanceof Collection) {
                    return AuthorityUtils.commaSeparatedStringToAuthorityList(StringUtils.collectionToCommaDelimitedString((Collection) authorities));
                } else {
                    throw new IllegalArgumentException("Authorities must be either a String or a Collection");
                }
            }
        }
    }

}
