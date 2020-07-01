package cn.toy.www.service.impl;


import cn.toy.www.dao.UserRoleDao;
import cn.toy.www.model.UserRole;
import cn.toy.www.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleDao userRoleDao;

	@Override
	public List<UserRole> findAll() {
		return userRoleDao.findAll();
	}

	@Override
	public UserRole findById(Long id) {
		return userRoleDao.findById(id);
	}

	@Override
	public int deleteById(Long id) {
		return userRoleDao.deleteById(id);
	}

	@Override
	public int update(UserRole userRole) {
		return userRoleDao.update(userRole);
	}

	@Override
	public int save(UserRole userRole) {
		return userRoleDao.save(userRole);
	}

}