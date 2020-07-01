package cn.toy.www.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;


@Setter
@Getter
@ToString
public class UserRole {
   /**创建人*/
   private Long createBy;
   /**创建时间*/
   private Timestamp createTime;
   /**id*/
   private Long id;
   /**是否删除：0-否；1-是*/
   private Byte delete;
   /**乐观锁*/
   private Integer revision;
   /**角色主键*/
   private Long roleId;
   /**更新人*/
   private Long updateBy;
   /**更新时间*/
   private Timestamp updateTime;
   /**用户主键*/
   private Long userId;
}