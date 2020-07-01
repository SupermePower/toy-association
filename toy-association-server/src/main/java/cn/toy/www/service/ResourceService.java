package cn.toy.www.service;

import cn.toy.www.ApiResultObject;
import cn.toy.www.model.Resource;
import cn.toy.www.request.ResourceDistributionRequest;
import cn.toy.www.vo.ResourceVO;

import java.util.List;

public interface ResourceService {

    List<Resource> findAll();

    Resource findById(Long id);

    int deleteById(Long id);

    int update(Resource resource);

    int save(Resource resource);

    /**
     * 获取资源信息
     *
     * @return 资源信息
     */
    ApiResultObject.ResultObject<List<ResourceVO>> resource();

    /**
     * 资源分配
     *
     * @param request 分配资源请求
     * @return 分配结果
     */
    ApiResultObject.ResultObject resourceDistribution(ResourceDistributionRequest request);

    /**
     * 获取角色拥有资源信息
     *
     * @param roleId 角色主键
     * @return 资源信息
     */
    ApiResultObject.ResultObject<List<ResourceVO>> queryRoleResource(Long roleId);
}