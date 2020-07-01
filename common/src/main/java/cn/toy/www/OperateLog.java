package cn.toy.www;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author: zhaobingyu
 * @Date: 4:27 PM 12/07/2018
 */
@Getter@Setter
public class OperateLog {


    /**
     *  服务名称
     */
    private String serviceName;

    /**
     * 操作人id
     */
    private String userId;

    /**
     * 操作人姓名
     */
    private String userName;

    /**
     * ip地址
     */
    private String ipAddress;

    /**
     * 操作记录
     */
    private String operateDetail;


    /**
     *  详情url
     */
    private String detailUrl;


    /**
     * 操作日期
     */
    private Date operateTime;


    @Override
    public String toString() {
        return "OperateLog{" +
                "serviceName='" + serviceName + '\'' +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", operateDetail='" + operateDetail + '\'' +
                ", detailUrl='" + detailUrl + '\'' +
                ", operateTime=" + operateTime +
                '}';
    }
}
