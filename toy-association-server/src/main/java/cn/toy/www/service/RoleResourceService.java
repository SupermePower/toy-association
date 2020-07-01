package cn.toy.www.service;


import cn.toy.www.model.RoleResource;

import java.util.List;

public interface RoleResourceService {

	List<RoleResource> findAll();

	RoleResource findById(Long id);

	int deleteById(Long id);

	int update(RoleResource roleResource);

	int save(RoleResource roleResource);

}