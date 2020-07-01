package cn.toy.www.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @description: 资源VO
 * @author: Zero
 * @date: 2020/4/12 下午2:05
 */
@Setter
@Getter
@ToString
public class ResourceVO {
    @ApiModelProperty(value = "资源ID")
    private Long id;
    @ApiModelProperty(value = "资源父ID")
    private Long parentId;
    @ApiModelProperty(value = "资源名称")
    private String resourceName;
    @ApiModelProperty(value = "资源名称说明")
    private String explanation;
    @ApiModelProperty(value = "资源icon")
    private String resourceIcon;
    @ApiModelProperty(value = "资源icon active")
    private String resourceIconActive;
    @ApiModelProperty(value = "资源地址")
    private String resourceUrl;
    @ApiModelProperty(value = "子资源")
    private List<ResourceVO> children;
}
