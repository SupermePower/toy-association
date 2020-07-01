package cn.toy.www.util.enums;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum ProgramDiskType {

    RAID(100, "磁盘阵列"),
    LOCALDISK(200, "本地硬盘");

    private Integer code;
    private String descr;

    ProgramDiskType(Integer code, String descr) {
        this.code = code;
        this.descr = descr;
    }
}
