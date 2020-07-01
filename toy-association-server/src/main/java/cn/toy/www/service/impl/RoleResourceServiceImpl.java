package cn.toy.www.service.impl;

import cn.toy.www.dao.RoleResourceDao;
import cn.toy.www.model.RoleResource;
import cn.toy.www.service.RoleResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleResourceServiceImpl implements RoleResourceService {

    @Autowired
    private RoleResourceDao roleResourceDao;

	@Override
	public List<RoleResource> findAll() {
		return roleResourceDao.findAll();
	}

	@Override
	public RoleResource findById(Long id) {
		return roleResourceDao.findById(id);
	}

	@Override
	public int deleteById(Long id) {
		return roleResourceDao.deleteById(id);
	}

	@Override
	public int update(RoleResource roleResource) {
		return roleResourceDao.update(roleResource);
	}

	@Override
	public int save(RoleResource roleResource) {
		return roleResourceDao.save(roleResource);
	}

}