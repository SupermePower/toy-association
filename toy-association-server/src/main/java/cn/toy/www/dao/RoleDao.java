package cn.toy.www.dao;

import cn.toy.www.request.role.UpdateRoleRequest;
import cn.toy.www.user.Role;
import cn.toy.www.vo.RoleVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: fly
 * @Description: 角色DAO
 * @Date: 2020/4/9
 */
@Repository
public interface RoleDao {

    void save(Role role);

    List<RoleVO> queryList();

    int deleteById(@Param("id") Long id, @Param("updateBy") Long updateBy);

    void update(UpdateRoleRequest request);
}
