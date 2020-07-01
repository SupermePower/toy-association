package cn.toy.www.service;


import cn.toy.www.ApiResultObject;
import cn.toy.www.request.LoginRequest;

/**
 * @description: 登录业务
 * @author: Zero
 * @date: 2020/4/7 下午9:41
 */
public interface LoginService {

    /**
     * 用户登录----账号密码
     *
     * @param request 账号 密码
     * @return access token
     */
    ApiResultObject.ResultObject accountLogin(LoginRequest request);
}
