package cn.toy.www.service.impl;

import cn.toy.www.ApiResultObject;
import cn.toy.www.dao.RoleDao;
import cn.toy.www.dao.UserRoleDao;
import cn.toy.www.idworker.IdWorker;
import cn.toy.www.model.UserRole;
import cn.toy.www.request.role.AddRoleRequest;
import cn.toy.www.request.role.UpdateRoleRequest;
import cn.toy.www.security.UserUtils;
import cn.toy.www.service.RoleService;
import cn.toy.www.user.Role;
import cn.toy.www.vo.RoleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.sql.Timestamp;
import java.util.List;

/**
 * @Author: fly
 * @Description: 角色业务
 * @Date: 2020/4/9
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private UserRoleDao userRoleDao;

    @Autowired
    private IdWorker idWorker;

    /**
     * 新增角色信息
     *
     * @param request 角色名称
     * @return 新增结果
     */
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    @Override
    public ApiResultObject.ResultObject addRole(AddRoleRequest request) {
        Role role = new Role();
        role.setId(idWorker.nextId());
        role.setRoleName(request.getRoleName());
        role.setUpdateBy(UserUtils.getCurrentUser().getId());
        role.setCreateBy(UserUtils.getCurrentUser().getId());
        roleDao.save(role);
        return ApiResultObject.createSuccessResult(null);
    }

    /**
     * 角色集合
     *
     * @return 角色机构
     */
    @Override
    public ApiResultObject.ResultObject<List<RoleVO>> roleList() {
        return ApiResultObject.createSuccessResult(roleDao.queryList());
    }

    /**
     * 删除角色信息
     *
     * @param id 角色ID
     * @return 删除结果
     */
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    @Override
    public ApiResultObject.ResultObject delete(Long id) {
        if (!CollectionUtils.isEmpty(userRoleDao.findByRoleId(id))) {
            return ApiResultObject.createDefaultCodeErrorResult("角色已被分配不可删除");
        }
        return ApiResultObject.createSuccessResult(roleDao.deleteById(id, UserUtils.getCurrentUser().getId()));
    }

    /**
     * 编辑角色
     *
     * @param request 角色ID，角色名称
     * @return 编辑结果
     */
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    @Override
    public ApiResultObject.ResultObject update(UpdateRoleRequest request) {
        roleDao.update(request);
        return ApiResultObject.createSuccessResult(null);
    }

    /**
     * 角色分配
     *
     * @param userId 用户ID
     * @param roleId 角色ID
     * @return 分配结果
     */
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    @Override
    public ApiResultObject.ResultObject distribution(Long userId, Long roleId) {
        userRoleDao.deleteByUserId(userId);
        UserRole userRole = new UserRole();
        userRole.setId(idWorker.nextId());
        userRole.setUserId(userId);
        userRole.setRoleId(roleId);
        userRole.setCreateBy(UserUtils.getCurrentUser().getId());
        userRole.setCreateTime(new Timestamp(System.currentTimeMillis()));
        userRole.setUpdateBy(UserUtils.getCurrentUser().getId());
        userRole.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        userRoleDao.save(userRole);
        return ApiResultObject.createSuccessResult(null);
    }
}
