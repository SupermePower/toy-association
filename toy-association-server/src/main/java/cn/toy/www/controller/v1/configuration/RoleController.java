package cn.toy.www.controller.v1.configuration;

import cn.toy.www.ApiResultObject;
import cn.toy.www.apiversion.ApiVersion;
import cn.toy.www.request.RoleDistributionRequest;
import cn.toy.www.request.role.AddRoleRequest;
import cn.toy.www.request.role.UpdateRoleRequest;
import cn.toy.www.service.RoleService;
import cn.toy.www.vo.RoleVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author: fly
 * @description: 角色控制器
 * @date: 2020/4/6
 */
@Api(tags = "角色配置", description = "role")
@RestController
@RequestMapping(path = "/{version}/role")
@ApiVersion
public class RoleController {

    @Autowired
    private RoleService roleService;

    @ApiOperation(value = "新增角色")
    @PostMapping(path = "/add")
    public ApiResultObject.ResultObject addRole(@RequestBody @Valid AddRoleRequest request) {
        return roleService.addRole(request);
    }

    @ApiOperation(value = "角色集合")
    @GetMapping(path = "/list")
    public ApiResultObject.ResultObject<List<RoleVO>> roleList() {
        return roleService.roleList();
    }

    @ApiOperation(value = "删除角色")
    @DeleteMapping(path = "{id}")
    public ApiResultObject.ResultObject delete(@PathVariable Long id) {
        return roleService.delete(id);
    }

    @ApiOperation(value = "编辑角色")
    @PutMapping
    public ApiResultObject.ResultObject update(@RequestBody @Valid UpdateRoleRequest request) {
        return roleService.update(request);
    }

    @ApiOperation(value = "角色分配")
    @PostMapping(path = "/distribution")
    public ApiResultObject.ResultObject distribution(@RequestBody @Valid RoleDistributionRequest request) {
        return roleService.distribution(request.getUserId(), request.getRoleId());
    }
}
