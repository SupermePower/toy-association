package cn.toy.www;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author: zhaobingyu
 * @Date: 4:18 PM 28/06/2018
 */
@Getter
@Setter
@ToString
public class AbstractBaseModel implements Serializable {


    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * id worker
     */
    @ApiModelProperty(value = "主键")
    private Long id;


    /**
     *  创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Timestamp createTime;



    /**
     * 更新时间
     */
    @ApiModelProperty(value = "修改时间")
    private Timestamp updateTime;

}
