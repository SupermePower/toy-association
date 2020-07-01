package cn.toy.www.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;


@Setter
@Getter
@ToString
public class Resource {
   /**创建人*/
   private Long createBy;
   /**创建时间*/
   private Timestamp createTime;
   /**主键*/
   private Long id;
   /**是否删除：0-否；1-是*/
   private Byte delete;
   /**父资源ID*/
   private Long parentId;
   /**资源图标*/
   private String resourceIcon;
   private String resourceIconActive;
   /**资源名称*/
   private String resourceName;
   /**资源地址*/
   private String resourceUrl;
   /**乐观锁*/
   private Integer revision;
   /**更新人*/
   private Long updateBy;
   /**更新时间*/
   private Timestamp updateTime;
}