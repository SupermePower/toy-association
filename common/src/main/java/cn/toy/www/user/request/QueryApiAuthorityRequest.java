package cn.toy.www.user.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;

/**
 * @Auther: zby
 * @Date: 18-11-1 11:30
 * @Description:
 */
@Getter@Setter@ToString
public class QueryApiAuthorityRequest {





    @ApiModelProperty(value = "功能id")
    private String functionId;




    @ApiModelProperty(value = "功能数组")
    private HashSet<String> functionIds;


    @ApiModelProperty(value = "权限点名称")
    private String authorityName;


    @ApiModelProperty(value = "权限点code")
    private String code;




}
