package cn.toy.www.service.impl;

import cn.toy.www.ApiResultObject;
import cn.toy.www.dao.ResourceDao;
import cn.toy.www.dao.RoleResourceDao;
import cn.toy.www.idworker.IdWorker;
import cn.toy.www.model.Resource;
import cn.toy.www.model.RoleResource;
import cn.toy.www.request.ResourceDistributionRequest;
import cn.toy.www.security.UserUtils;
import cn.toy.www.service.ResourceService;
import cn.toy.www.vo.ResourceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceDao resourceDao;

    @Autowired
    private RoleResourceDao roleResourceDao;

    @Autowired
    private IdWorker idWorker;

    @Override
    public List<Resource> findAll() {
        return resourceDao.findAll();
    }

    @Override
    public Resource findById(Long id) {
        return resourceDao.findById(id);
    }

    @Override
    public int deleteById(Long id) {
        return resourceDao.deleteById(id);
    }

    @Override
    public int update(Resource resource) {
        return resourceDao.update(resource);
    }

    @Override
    public int save(Resource resource) {
        return resourceDao.save(resource);
    }

    /**
     * 获取资源信息
     *
     * @return 资源信息
     */
    @Override
    public ApiResultObject.ResultObject<List<ResourceVO>> resource() {
        List<ResourceVO> resourceVOList = resourceDao.resourceList();
        List<ResourceVO> parentResourceList = resourceVOList.stream().filter(resource -> resource.getParentId().equals(0L)).collect(Collectors.toList());
        for (ResourceVO parent : parentResourceList) {
            parent.setChildren(resourceVOList.stream().filter(resourceVO -> resourceVO.getParentId().equals(parent.getId())).collect(Collectors.toList()));
        }
        return ApiResultObject.createSuccessResult(parentResourceList);
    }

    /**
     * 资源分配
     *
     * @param request 分配资源请求
     * @return 分配结果
     */
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    @Override
    public ApiResultObject.ResultObject resourceDistribution(ResourceDistributionRequest request) {
        roleResourceDao.deleteByRoleId(request.getRoleId());
        for (Long resourceId : request.getResourceIdList()) {
            RoleResource roleResource = new RoleResource();
            roleResource.setId(idWorker.nextId());
            roleResource.setRoleId(request.getRoleId());
            roleResource.setResourceId(resourceId);
            roleResource.setCreateTime(new Timestamp(System.currentTimeMillis()));
            roleResource.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            roleResource.setCreateBy(UserUtils.getCurrentUser().getId());
            roleResource.setUpdateBy(UserUtils.getCurrentUser().getId());
            roleResourceDao.save(roleResource);
        }
        return ApiResultObject.createSuccessResult(null);
    }

    /**
     * 获取角色拥有资源信息
     *
     * @param roleId 角色主键
     * @return 资源信息
     */
    @Override
    public ApiResultObject.ResultObject<List<ResourceVO>> queryRoleResource(Long roleId) {
        List<ResourceVO> resourceVOList = resourceDao.findByRoleId(roleId);
        List<ResourceVO> parentResourceList = resourceVOList.stream().filter(resource -> resource.getParentId().equals(0L)).collect(Collectors.toList());
        for (ResourceVO parent : parentResourceList) {
            parent.setChildren(resourceVOList.stream().filter(resourceVO -> resourceVO.getParentId().equals(parent.getId())).collect(Collectors.toList()));
        }
        return ApiResultObject.createSuccessResult(parentResourceList);
    }
}