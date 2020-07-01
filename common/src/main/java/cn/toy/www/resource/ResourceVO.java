package cn.toy.www.resource;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author zby
 * @Description:
 * @Date: 上午10:36 2018/7/19
 */
@Getter@Setter
public class ResourceVO {


    private String resourceId;
    /**资源url*/
    private String resourceUrl;
    /**资源名称*/
    private String resourceName;
    /**资源类型*/
    private String resourceType;

    @Override
    public String toString() {
        return "ResourceVO{" +
                "resourceId='" + resourceId + '\'' +
                ", resourceUrl='" + resourceUrl + '\'' +
                ", resourceName='" + resourceName + '\'' +
                ", resourceType='" + resourceType + '\'' +
                '}';
    }
}
