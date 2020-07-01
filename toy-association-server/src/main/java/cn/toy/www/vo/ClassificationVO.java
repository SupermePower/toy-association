package cn.toy.www.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @description: 分类信息
 * @author: Zero
 * @date: 2020/4/12 下午11:50
 */
@Setter
@Getter
@ToString
public class ClassificationVO {
    @ApiModelProperty(value = "主键")
    private Long id;
    @ApiModelProperty(value = "父级ID")
    private Long parentId;
    @ApiModelProperty(value = "分类名称")
    private String name;
    @ApiModelProperty(value = "子分类名称")
    private List<ClassificationVO> children;
}
