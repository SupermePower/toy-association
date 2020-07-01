package cn.toy.www.uaa.service;


import cn.toy.www.uaa.dao.UaaUserDao;
import cn.toy.www.user.AuthorityPoint;
import cn.toy.www.user.SecurityUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
public class CustomerUserDetailServiceImpl implements UserDetailsService {

    @Override
    public SecurityUser loadUserByUsername(String username) throws UsernameNotFoundException {
        SecurityUser securityUser = uaaUserDao.findByUserName(username);
        List<AuthorityPoint> authorityPoints = getAuthorityPoints();
        securityUser.setAuthorities(authorityPoints);
        return securityUser;
    }

    /**
     * 获取当前用户权限点
     *
     * @return 权限点
     */
    private List<AuthorityPoint> getAuthorityPoints() {
        List<AuthorityPoint> authorityPoints = new ArrayList<>();
        AuthorityPoint authorityPoint = new AuthorityPoint();
        authorityPoint.setCode("ROLE_ADMIN");
        authorityPoints.add(authorityPoint);
        return authorityPoints;
    }

    public SecurityUser loadUserByUserId(String userId) throws UsernameNotFoundException {
        return uaaUserDao.findById(userId);
    }

    public boolean qrCode(String password) {
        return "yundoumedia".equals(password);
    }

    @Autowired
    private UaaUserDao uaaUserDao;
}
