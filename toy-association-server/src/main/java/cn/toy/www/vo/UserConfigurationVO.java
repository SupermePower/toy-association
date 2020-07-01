package cn.toy.www.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @description: 人员配置
 * @author: Zero
 * @date: 2020/5/5 下午4:33
 */
@Setter
@Getter
@ToString
public class UserConfigurationVO {
    @ApiModelProperty(value = "角色ID")
    private Long roleId;
    @ApiModelProperty(value = "管理平台：1-云豆本部；2-云豆律所")
    private Byte managementPlatform;
    @ApiModelProperty(value = "律所ID")
    private Long lawOfficeId;
    @ApiModelProperty(value = "平台ID集合")
    private List<Long> platformIdList;
}
