package cn.toy.www.controller.v1.login;

import cn.toy.www.ApiResultObject;
import cn.toy.www.apiversion.ApiVersion;
import cn.toy.www.request.LoginRequest;
import cn.toy.www.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @description: 登录控制器
 * @author: Zero
 * @date: 2020/4/7 下午9:38
 */
@RestController
@RequestMapping(path = "{version}/login")
@Api(value = "login", tags = "登录模块")
@ApiVersion
public class LoginController {

    @Autowired
    private LoginService loginService;

    @ApiOperation(value = "账号密码登录")
    @PostMapping(path = "/account")
    public ApiResultObject.ResultObject accountLogin(@RequestBody @Valid LoginRequest request) {
        return loginService.accountLogin(request);
    }
}
