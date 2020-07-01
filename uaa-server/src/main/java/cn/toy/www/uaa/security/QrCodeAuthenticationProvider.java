package cn.toy.www.uaa.security;

import cn.toy.www.uaa.service.CustomerUserDetailServiceImpl;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

public class QrCodeAuthenticationProvider extends DaoAuthenticationProvider {
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(
                QrCodeVerificationAuthenticationToken.class);
    }

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        Assert.isInstanceOf(QrCodeVerificationAuthenticationToken.class, authentication,
                messages.getMessage("AbstractUserDetailsAuthenticationProvider.onlySupports",
                        "Only SMSVerificationAuthenticationToken is supported"));

        // Determine username
        String username = (authentication.getPrincipal() == null) ? "NONE_PROVIDED" : authentication.getName();

        boolean cacheWasUsed = true;
        UserDetails user = this.getUserCache().getUserFromCache(username);

        CustomerUserDetailServiceImpl customUserDetailsService = (CustomerUserDetailServiceImpl)this.getUserDetailsService();

        if (user == null) {
            cacheWasUsed = false;

            try {
                verificationAuthenticationChecks(customUserDetailsService, username, (QrCodeVerificationAuthenticationToken) authentication);
                user = retrieveUser(username, new UsernamePasswordAuthenticationToken(authentication.getPrincipal(),
                        authentication.getCredentials(), authentication.getAuthorities()));
            } catch (AuthenticationException exception) {
                logger.debug("User '" + username + "' not found");

                if (hideUserNotFoundExceptions) {
                    throw new BadCredentialsException(messages.getMessage(
                            "AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
                } else {
                    throw exception;
                }
            }

            Assert.notNull(user, "retrieveUser returned null - a violation of the interface contract");
        }

        try {
            getPreAuthenticationChecks().check(user);
            //additionalAuthenticationChecks(user, (UsernamePasswordAuthenticationToken) authentication);
        } catch (AuthenticationException exception) {
            if (cacheWasUsed) {
                // There was a problem, so try again after checking
                // we're using latest data (i.e. not from the cache)
                cacheWasUsed = false;
                user = retrieveUser(username, new UsernamePasswordAuthenticationToken(authentication.getPrincipal(),
                        authentication.getCredentials(), authentication.getAuthorities()));
                getPreAuthenticationChecks().check(user);
                //additionalAuthenticationChecks(user, (UsernamePasswordAuthenticationToken) authentication);
            } else {
                throw exception;
            }
        }

        getPostAuthenticationChecks().check(user);

        if (!cacheWasUsed) {
            this.getUserCache().putUserInCache(user);
        }

        Object principalToReturn = user;

        if (isForcePrincipalAsString()) {
            principalToReturn = user.getUsername();
        }

        // 如果原Authentication中用户名与获取到的UserDetails不一致,则返回UserDetails中用户名
        if (!username.equals(user.getUsername())){
            authentication = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        }

        return createSuccessAuthentication(principalToReturn, authentication, user);
    }

    private void verificationAuthenticationChecks(CustomerUserDetailServiceImpl customUserDetailsService, String username,
                                                  QrCodeVerificationAuthenticationToken authentication) throws AuthenticationException {
        if (authentication.getCredentials() == null) {
            logger.debug("Authentication failed: no credentials provided");

            throw new BadCredentialsException(messages.getMessage(
                    "AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
        }
        String code = authentication.getCredentials().toString();

        // 对用户输入的验证码进行有效性验证
        if (!customUserDetailsService.qrCode(code)) {
            logger.debug("Authentication failed: password does not match stored value");

            throw new BadCredentialsException(messages.getMessage(
                    "AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
        }
    }

}
