package cn.toy.www.request.log;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * 保存用户的操作日志
 *
 * @author:chenhongyu
 * @date:2018-10-31 01:53
 */
@Getter
@Setter
@ToString
public class SaveUserOperationLog {

    /**
     * 操作
     */
    @ApiModelProperty(value = "操作内容", required = true)
    @NotNull(message = "操作内容不能为空")
    private String info;

    /**
     * ip地址
     */
    @ApiModelProperty(value = "IP", required = true)
    @NotNull(message = "IP不能为空")
    private String ipAddress;

    /**
     * 模块ID
     * 用来区分是否是一个子系统（例如商务部子系统）
     */
    @ApiModelProperty(value = "模块ID", required = false)
    private String moduleId;

    /**
     * 操作ID
     * 通常是接口上定义的，用来区分接口
     */
    @ApiModelProperty(value = "操作ID", required = false)
    private String operationId;
}
