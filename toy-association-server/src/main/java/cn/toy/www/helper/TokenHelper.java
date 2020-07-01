package cn.toy.www.helper;

import cn.toy.www.client.UaaClient;
import cn.toy.www.request.LoginRequest;
import cn.toy.www.security.JWT;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author fly
 * @description 获取token
 * @date 2019/5/13 16:22
 */
@Component
public class TokenHelper {

    @Resource
    private UaaClient uaaClient;

    /**
     * 获取token
     *
     * @param request 登录请求
     * @return token信息
     */
    public JWT getToken(LoginRequest request) {
        return uaaClient.getToken("Basic eG14Yy1zZXJ2aWNlOjdtdmg4cTVnNW5rYWlpazFudThsdG41NzlzN2E4eTFv",
                "password",
                request.getMobile(),
                request.getPassword());
    }

    /**
     * 获取token
     *
     * @param request 登录请求
     * @return token信息
     */
    public JWT getTokenByQrCode(LoginRequest request) {
        return uaaClient.getToken("Basic eG14Yy1zZXJ2aWNlOjdtdmg4cTVnNW5rYWlpazFudThsdG41NzlzN2E4eTFv",
                "qr_code",
                request.getMobile(),
                request.getPassword());
    }
}
