package cn.toy.www.service;

import cn.toy.www.ApiResultObject;
import cn.toy.www.request.role.AddRoleRequest;
import cn.toy.www.request.role.UpdateRoleRequest;
import cn.toy.www.vo.RoleVO;
import java.util.List;

/**
 * @Author: fly
 * @Description: 角色业务
 * @Date: 2020/4/9
 */
public interface RoleService {

    /**
     * 新增角色信息
     *
     * @param request 角色名称
     * @return 新增结果
     */
    ApiResultObject.ResultObject addRole(AddRoleRequest request);

    /**
     * 角色集合
     *
     * @return 角色机构
     */
    ApiResultObject.ResultObject<List<RoleVO>> roleList();

    /**
     * 删除角色信息
     *
     * @param id 角色ID
     * @return 删除结果
     */
    ApiResultObject.ResultObject delete(Long id);

    /**
     * 编辑角色
     *
     * @param request 角色ID，角色名称
     * @return 编辑结果
     */
    ApiResultObject.ResultObject update(UpdateRoleRequest request);

    /**
     * 角色分配
     *
     * @param userId 用户ID
     * @param roleId 角色ID
     * @return 分配结果
     */
    ApiResultObject.ResultObject distribution(Long userId, Long roleId);
}
