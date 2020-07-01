package cn.toy.www.util.enums;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum DateUnitTypeEnum {

    YEAR(0, "年"),
    MONTH(1,"月"),
    DAY(2,"日");


    private Integer code;
    private String descr;

    DateUnitTypeEnum(Integer code, String descr) {
        this.code = code;
        this.descr = descr;
    }
}
