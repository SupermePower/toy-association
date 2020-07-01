package cn.toy.www.util.enums;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum RightChartType {

    COPYRIGHT(0, "版权方"),
    PLATFORM(1, "平台");

    private Integer code;
    private String descr;

    RightChartType(Integer code, String descr) {
        this.code = code;
        this.descr = descr;
    }
}
