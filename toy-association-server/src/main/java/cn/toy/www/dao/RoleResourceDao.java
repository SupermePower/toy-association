package cn.toy.www.dao;

import cn.toy.www.model.RoleResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleResourceDao {

	List<RoleResource> findAll();

	RoleResource findById(Long id);

	int deleteById(Long id);

	int update(RoleResource roleResource);

	int save(RoleResource roleResource);

	int deleteByRoleId(Long roleId);
}