package cn.toy.www.util.enums;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum TranslateTaskStatus {

    INIT(100, "未开始"),
    PROCESSING(200, "进行中"),
    DELAY(210, "延期"),
    OVER(220, "提前"),
    NORMAL(230, "正常"),
    COMPLETE(300, "完成");

    private Integer code;
    private String descr;

    TranslateTaskStatus(Integer code, String descr) {
        this.code = code;
        this.descr = descr;
    }
}
