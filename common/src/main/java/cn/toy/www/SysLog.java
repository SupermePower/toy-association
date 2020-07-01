package cn.toy.www;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author: zhaobingyu
 * @Date: 7:50 PM 12/07/2018
 */
@Getter@Setter
public class SysLog {

    /**
     * 服务名
     */
    private String serviceName;

    /**
     * 方法名
     */
    private String method;

    /**
     * 操作人id
     */
    private String userId;

    /**
     * 操作人
     */
    private String userName;

    /**
     * 请求参数
     */
    private String methodParams;


    /**
     * ip地址
     */
    private String ipAddress;

    /**
     * 请求时间
     */
    private Date requestTime;

    /**
     * 返回结果
     */
    private String result;
}
