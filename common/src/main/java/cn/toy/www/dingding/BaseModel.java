package cn.toy.www.dingding;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author zhaobingyu
 * @Date 2017/8/2 14:36
 */
@Data
public class BaseModel implements Serializable {



    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */
    private  Long id;

    /**
     * 创建时间
     */
    private Timestamp createTime;


    /**
     * 更新时间
     */
    private Timestamp updateTime;





    protected String createTimeString;


    protected String updateTimeString;

}
