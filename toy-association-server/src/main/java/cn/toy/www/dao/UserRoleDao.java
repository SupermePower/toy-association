package cn.toy.www.dao;

import cn.toy.www.model.UserRole;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleDao {

	List<UserRole> findAll();

	UserRole findById(Long id);

	int deleteById(Long id);

	int update(UserRole userRole);

	int save(UserRole userRole);

	int deleteByUserId(Long userId);

	UserRole findByUserId(Long userId);

	List<UserRole> findByRoleId(Long roleId);
}