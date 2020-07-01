package cn.toy.www.util;

import java.util.UUID;

/**
 * @description 获取UUID
 * @author: fly
 * @Date: 2018/7/17 下午12:10
 */
public final class UUIDHelper {

    private UUIDHelper() {
    }

    /**
     * 获取UUID
     *
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }
}
