package cn.toy.www.request.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @program: xmxc
 * @description: 获取折扣审批人请求
 * @author: Leo Chen
 * @Date: 2019-10-18 10:34
 **/
@Getter
@Setter
@ToString
public class GetApproveUserRequest {

    /**
     * 实例ID
     */
    @NotNull(message = "实例ID不可为空")
    private Long projectId;

    /**
     * poolId
     */
    @NotNull(message = "poolId不可为空")
    private Long poolId;

    /**
     * 需要审核的级别
     */
    @NotNull(message = "level不可为空")
    private Integer level;

    /**
     * 申请人用户ID
     */
    @NotNull(message = "申请人用户ID")
    private String applyUserId;

}
