package cn.toy.www.client;

import cn.toy.www.security.JWT;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @description: 授权客户端
 * @author: Zero
 * @date: 2020/4/16 下午9:45
 */
@FeignClient(value = "uaa-service")
@Service
public interface UaaClient {

    /**
     * 获取token
     *
     * @param authorization
     * @param type
     * @param username
     * @param password
     * @return
     */
    @PostMapping(value = "/uaa/oauth/token")
    JWT getToken(@RequestHeader(value = "Authorization") String authorization, @RequestParam("grant_type") String type,
                 @RequestParam("username") String username, @RequestParam("password") String password);

}
