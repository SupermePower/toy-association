package cn.toy.www.controller.v1.configuration;

import cn.toy.www.ApiResultObject;
import cn.toy.www.apiversion.ApiVersion;
import cn.toy.www.request.QueryUserListRequest;
import cn.toy.www.service.UserService;
import cn.toy.www.vo.*;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @description: 用户控制器
 * @author: Zero
 * @date: 2020/4/6 下午5:20
 */
@RestController
@RequestMapping(path = "/{version}/user")
@Api(value = "user", tags = "用户管理")
@ApiVersion
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "删除人员信息")
    @DeleteMapping(path = "delete/{id}")
    public ApiResultObject deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }

    @ApiOperation(value = "重置密码")
    @PutMapping(path = "password/reset/{id}")
    public ApiResultObject.ResultObject resetPassword(@PathVariable Long id) {
        return userService.resetPassword(id);
    }

    @ApiOperation(value = "获取用户列表")
    @GetMapping(path = "list")
    public ApiResultObject.ResultObject<PageInfo<UserListVO>> queryUserList(@Valid QueryUserListRequest request) {
        return userService.selectPage(request);
    }

    @ApiOperation(value = "下拉获取用户信息")
    @PostMapping(path = "/selected/list")
    public ApiResultObject.ResultObject<List<UserSelectedListVO>> queryUserSelectedList() {
        return userService.queryUserSelectedList();
    }

    @ApiOperation(value = "获取当前用户详情")
    @GetMapping(path = "/detail")
    public ApiResultObject.ResultObject<UserDetailVO> useCurrentDetail() {
        return userService.useCurrentDetail();
    }
}
