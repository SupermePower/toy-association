package cn.toy.www.service.impl;

import cn.toy.www.ApiResultObject;
import cn.toy.www.ErrorCode;
import cn.toy.www.dao.ResourceDao;
import cn.toy.www.dao.UserDao;
import cn.toy.www.dao.UserRoleDao;
import cn.toy.www.dingding.User;
import cn.toy.www.idworker.IdWorker;
import cn.toy.www.request.QueryUserListRequest;
import cn.toy.www.request.user.AddUerRequest;
import cn.toy.www.request.user.UpdateUserRequest;
import cn.toy.www.security.BpwdEncoder;
import cn.toy.www.security.UserUtils;
import cn.toy.www.service.RoleService;
import cn.toy.www.service.UserService;
import cn.toy.www.vo.UserDetailVO;
import cn.toy.www.vo.UserListVO;
import cn.toy.www.vo.UserSelectedListVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * @Author: Zero
 * @Description: 用户业务
 * @Date: 2020/4/8
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private UserRoleDao userRoleDao;

    @Autowired
    private ResourceDao resourceDao;

    @Autowired
    private RoleService roleService;

    /**
     * 创建用户信息
     *
     * @param request 用户信息
     * @return 创建结果
     */
    @Override
    public ApiResultObject addUser(AddUerRequest request) {

        User userByTel = userDao.getUserByMobile(request.getMobile());
        if (userByTel != null) {
            return ApiResultObject.createDefaultCodeErrorResult(ErrorCode.TEL_IS_HAD_ERROR.getMsg());
        }

        // 保存用户信息
        User user = new User();
        user.setId(idWorker.nextId());
        user.setPassword(BpwdEncoder.bcryptPassword(request.getMobile().substring(7, request.getMobile().length())));
        user.setEmail(request.getEmail());
        user.setMobile(request.getMobile());
        user.setAvatar(request.getAvatar());
        user.setUserName(request.getUserName());
        user.setPosition(request.getPosition());
        user.setCreateTime(new Timestamp(System.currentTimeMillis()));
        user.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        userDao.saveUser(user);
        return new ApiResultObject();
    }

    /**
     * 修改状态为离职
     *
     * @param id 用户主键
     * @return 修改结果
     */
    @Override
    public ApiResultObject resignation(Long id) {
        User user = userDao.findById(id);
        if (user == null) {
            return ApiResultObject.createDefaultCodeErrorResult(ErrorCode.USER_NOT_FOUND.getMsg());
        }
        userDao.updateStatus((byte) 0, id, UserUtils.getCurrentUser().getId());
        return new ApiResultObject();
    }

    /**
     * 修改状态为在职职
     *
     * @param id 用户主键
     * @return 修改结果
     */
    @Override
    public ApiResultObject inService(Long id) {
        User user = userDao.findById(id);
        if (user == null) {
            return ApiResultObject.createDefaultCodeErrorResult(ErrorCode.USER_NOT_FOUND.getMsg());
        }
        userDao.updateStatus((byte) 1, id, UserUtils.getCurrentUser().getId());
        return new ApiResultObject();
    }

    /**
     * 获取用户详情
     *
     * @param id 用户主键
     * @return 用户信息
     */
    @Override
    public ApiResultObject<User> userDetail(Long id) {
        return ApiResultObject.createSuccessResult(userDao.findById(id));
    }

    /**
     * 删除用户信息
     *
     * @param id 用户主键
     * @return 删除结果
     */
    @Override
    public ApiResultObject deleteUser(Long id) {
        userDao.deleteUser(id, UserUtils.getCurrentUser().getId());
        return new ApiResultObject();
    }

    /**
     * 编辑用户信息
     *
     * @param request 用户信息
     * @return 编辑结果
     */
    @Override
    public ApiResultObject updateUser(UpdateUserRequest request) {
        //TODO 修改用户信息
        return new ApiResultObject();
    }

    /**
     * 重置密码
     *
     * @param id 用户主键
     * @return 重置结果
     */
    @Override
    public ApiResultObject.ResultObject resetPassword(Long id) {
        User user = userDao.findById(id);
        if (user == null) {
            return ApiResultObject.createDefaultCodeErrorResult(ErrorCode.USER_NOT_FOUND.getMsg());
        }
        String password = BpwdEncoder.bcryptPassword(user.getMobile().substring(7, user.getMobile().length()));
        userDao.resetPassword(id, UserUtils.getCurrentUser().getId(), password);
        return ApiResultObject.createSuccessResult(null);
    }

    /**
     * 获取用户下拉列表
     *
     * @return 用户下拉列表
     */
    @Override
    public ApiResultObject.ResultObject<List<UserSelectedListVO>> queryUserSelectedList() {
        return ApiResultObject.createSuccessResult(userDao.queryUserSelectedList());
    }


    /**
     * 获取当前用户详情
     *
     * @return 当前用户详情
     */
    @Override
    public ApiResultObject.ResultObject<UserDetailVO> useCurrentDetail() {
        Long userId = UserUtils.getCurrentUser().getId();
        User user = userDao.findById(userId);
        UserDetailVO userDetailVO = new UserDetailVO();
        userDetailVO.setId(user.getId());
        userDetailVO.setName(user.getName());
        userDetailVO.setMobile(user.getMobile());
        userDetailVO.setAvatar(user.getAvatar());
        return ApiResultObject.createSuccessResult(userDetailVO);
    }

    /**
     * 分页获取用户信息
     *
     * @param request 请求参数
     * @return 用户信息
     */
    @Override
    public ApiResultObject.ResultObject<PageInfo<UserListVO>> selectPage(QueryUserListRequest request) {
        PageHelper.startPage(request.getStartPage(), request.getPageSize());

        List<UserListVO> userList = userDao.selectPage(request);

        return ApiResultObject.createSuccessResult(new PageInfo<>(userList));
    }
}
