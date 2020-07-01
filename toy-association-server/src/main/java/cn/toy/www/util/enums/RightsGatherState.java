package cn.toy.www.util.enums;

import lombok.Getter;
import lombok.ToString;

/**
 * 内容采集 - 处理结果状态
 */
@Getter
@ToString
public enum RightsGatherState {

    UNALLOC(100, "未分配"),
    ALLOCED(110, "已分配"),
    PROCESSING(200, "进行中"),
    END(300, "已完成");

    private Integer code;
    private String descr;
    RightsGatherState(Integer code, String descr) {
        this.code = code;
        this.descr = descr;
    }
}
