package cn.toy.www.service;


import cn.toy.www.model.UserRole;

import java.util.List;

public interface UserRoleService {

	List<UserRole> findAll();

	UserRole findById(Long id);

	int deleteById(Long id);

	int update(UserRole userRole);

	int save(UserRole userRole);

}