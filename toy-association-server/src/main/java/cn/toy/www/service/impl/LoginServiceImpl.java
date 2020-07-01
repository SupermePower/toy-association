package cn.toy.www.service.impl;


import cn.toy.www.ApiResultObject;
import cn.toy.www.ErrorCode;
import cn.toy.www.dao.UserDao;
import cn.toy.www.dingding.User;
import cn.toy.www.helper.TokenHelper;
import cn.toy.www.request.LoginRequest;
import cn.toy.www.security.BpwdEncoder;
import cn.toy.www.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @description: 登录业务
 * @author: Zero
 * @date: 2020/4/7 下午9:42
 */
@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private TokenHelper tokenHelper;

    /**
     * 账号密码方式登录
     *
     * @param request 账号 密码
     * @return access token
     */
    @Override
    public ApiResultObject.ResultObject accountLogin(LoginRequest request) {
        // 校验用户信息
        User user = userDao.getUserByMobile(request.getMobile());
        if (user == null) {
            return ApiResultObject.createDefaultCodeErrorResult(ErrorCode.USER_NOT_FOUND.getMsg());
        }
        // 校验密码
        boolean matches = BpwdEncoder.matches(request.getPassword(), user.getPassword());
        if (!matches) {
            return ApiResultObject.createDefaultCodeErrorResult(ErrorCode.USER_PASSWORD_ERROR.getMsg());
        }
        tokenHelper.getToken(request);
        return ApiResultObject.createSuccessResult(tokenHelper.getToken(request));
    }
}
