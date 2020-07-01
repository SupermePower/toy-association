package cn.toy.www.service;

import cn.toy.www.ApiResultObject;
import cn.toy.www.dingding.User;
import cn.toy.www.request.QueryUserListRequest;
import cn.toy.www.request.user.AddUerRequest;
import cn.toy.www.request.user.UpdateUserRequest;
import cn.toy.www.vo.UserDetailVO;
import cn.toy.www.vo.UserListVO;
import cn.toy.www.vo.UserSelectedListVO;
import com.github.pagehelper.PageInfo;

import java.util.List;


/**
 * @Author: Zero
 * @Description: 用户业务
 * @Date: 2020/4/8
 */
public interface UserService {

    /**
     * 创建用户信息
     *
     * @param request 用户信息
     * @return 创建结果
     */
    ApiResultObject addUser(AddUerRequest request);

    /**
     * 修改状态为离职
     *
     * @param id 用户主键
     * @return 修改结果
     */
    ApiResultObject resignation(Long id);

    /**
     * 修改状态为在职职
     *
     * @param id 用户主键
     * @return 修改结果
     */
    ApiResultObject inService(Long id);

    /**
     * 获取用户详情
     *
     * @param id 用户主键
     * @return 用户信息
     */
    ApiResultObject<User> userDetail(Long id);

    /**
     * 删除用户信息
     *
     * @param id 用户主键
     * @return 删除结果
     */
    ApiResultObject deleteUser(Long id);

    /**
     * 编辑用户信息
     *
     * @param request 用户信息
     * @return 编辑结果
     */
    ApiResultObject updateUser(UpdateUserRequest request);

    /**
     * 重置密码
     *
     * @param id 用户主键
     * @return 重置结果
     */
    ApiResultObject.ResultObject resetPassword(Long id);

    /**
     * 分页获取用户信息
     *
     * @param request 请求参数
     * @return 用户信息
     */
    ApiResultObject.ResultObject<PageInfo<UserListVO>> selectPage(QueryUserListRequest request);

    /**
     * 获取用户下拉列表
     *
     * @return 用户下拉列表
     */
    ApiResultObject.ResultObject<List<UserSelectedListVO>> queryUserSelectedList();

    /**
     * 获取当前用户详情
     *
     * @return 当前用户详情
     */
    ApiResultObject.ResultObject<UserDetailVO> useCurrentDetail();

}
