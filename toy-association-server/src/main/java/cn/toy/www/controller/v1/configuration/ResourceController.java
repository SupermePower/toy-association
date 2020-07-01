package cn.toy.www.controller.v1.configuration;

import cn.toy.www.ApiResultObject;
import cn.toy.www.apiversion.ApiVersion;
import cn.toy.www.request.ResourceDistributionRequest;
import cn.toy.www.service.ResourceService;
import cn.toy.www.vo.ResourceVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @description: 资源控制器
 * @author: Zero
 * @date: 2020/4/11 上午7:38
 */
@Slf4j
@RestController
@RequestMapping(path = "/{version}/resource")
@Api(tags = "系统资源", value = "resource")
@ApiVersion
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @ApiOperation(value = "获取资源", notes = "获取资源")
    @GetMapping
    public ApiResultObject.ResultObject<List<ResourceVO>> resource() {
        return resourceService.resource();
    }

    @ApiOperation(value = "资源分配", notes = "获取资源")
    @PostMapping(path = "/distribution")
    public ApiResultObject.ResultObject resourceDistribution(@RequestBody @Valid ResourceDistributionRequest request) {
        return resourceService.resourceDistribution(request);
    }

    @ApiOperation(value = "获取资源", notes = "获取资源")
    @GetMapping(path = "queryRoleResource/{roleId}")
    public ApiResultObject.ResultObject<List<ResourceVO>> queryRoleResource(@PathVariable Long roleId) {
        return resourceService.queryRoleResource(roleId);
    }
}
