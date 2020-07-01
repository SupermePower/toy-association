package cn.toy.www.dao;

import cn.toy.www.model.Resource;
import cn.toy.www.vo.ResourceVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceDao {

	List<Resource> findAll();

	List<ResourceVO> resourceList();

	List<ResourceVO> findByRoleId(Long roleId);

	Resource findById(Long id);

	int deleteById(Long id);

	int update(Resource resource);

	int save(Resource resource);
}