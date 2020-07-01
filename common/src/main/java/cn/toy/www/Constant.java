package cn.toy.www;

import com.google.common.collect.ImmutableMap;
import lombok.Getter;
import org.apache.http.HttpStatus;

import java.util.Map;

/**
 * @author: zhaobingyu
 * @Date: 6:33 PM 28/06/2018
 */
public class Constant {

    /**
     * 微服务名统一管理
     */
    public static class Service {
        public final static String MOVIE_SERVICE = "movie-service";
    }


    /**
     * 登录用户名和密码
     */
    public final static String USER_LOGIN_USERNAME = "admin";
    public final static String USER_LOGIN_PASSWORD = "123456";

    /**
     * reids 黑名单 key
     */
    public final static String BLACK_LIST_KEY = "BLACK_LIST:";


    /**
     * redis PC用户登录key   value为 token
     */
    public final static String USER_AUTH_KEY = "USER_AUTH_TOKEN:";


    /**
     * redis APP用户登录key   value为 token
     */
    public final static String APP_USER_AUTH_KEY = "APP_USER_AUTH_TOKEN:";

    /**
     * 需要剔除的token的key
     */
    public final static String NEED_EXCLUDE_TOKEN_KEY = "need_exclude_token:";

    /**
     * 时间格式化，判断是为个位数
     */
    public final static int MIN_TWO_DIGITS = 10;

    /**
     * map的默认值
     */
    public static final int DEFAULT_MAP_SIZE = 16;

    public static class Code implements HttpStatus {

        /**
         * feign客户端调用失败
         */
        public final static int SC_FEIGN_FALLBACK = 5001;


        /**
         * 用户重新登录 提示 code
         */
        public final static int USER_AUTH_CODE = 10401;


    }

    public static class Message {
        public final static String SC_OK = "操作成功.";
        public final static String SC_INTERNAL_SERVER_ERROR = "操作失败(服务器内部错误).";
        public final static String SC_FEIGN_FALLBACK = "服务器开小差了~请稍后重试!";
        public final static String SC_FORBIDDEN = "没有权限访问该资源~";
        public final static String SC_UNAUTHORIZED = "授权失败~";
    }

    public static class Auth {
        public final static String TOKEN_HEADER = "Authorization";
        public final static String TOKEN_VALUE_PREV = "bearer ";
        public final static String HTTP_BASIC_PREV = "Basic ";
        public final static String GRANT_TYPE_PASSWORD = "password";
        public final static String GRANT_TYPE_SMS = "sms";
        public final static String GRANT_TYPE_NOPASSWORD = "nopassword";
        public final static String GRANT_TYPE_REFRESH_TOKEN = "refresh_token";
    }


    public static class ServiceClient {
        public final static String CLIENT_ID = "xmxc-service";
        public final static String CLIENT_SECRET = "{noop}7mvh8q5g5nkaiik1nu8ltn579s7a8y1o";
        public final static String SCOPE = "service";
        /**
         * clientId + : + clientSecret  base64 转码
         */
        public final static String BASIC_AUTHENTICATION = "eG14Yy1zZXJ2aWNlOjdtdmg4cTVnNW5rYWlpazFudThsdG41NzlzN2E4eTFv";
    }

    /**
     * 数字常量类
     */
    public static class Number {
        /**
         * 数字0
         */
        public final static int NUM_0 = 0;

        /**
         * 数字1
         */
        public final static int NUM_1 = 1;
        /**
         * 数字2
         */
        public final static int NUM_2 = 2;
        /**
         * 数字3
         */
        public final static int NUM_3 = 3;
        /**
         * 数字4
         */
        public final static int NUM_4 = 4;
    }

    /**
     * 表格用到的常量
     */
    public static class Excel {

        /**
         * 两个词ASCII码相同
         */
        public final static int WORD_SAME = 0;

        /**
         * 两个词ASCII码完全不同
         */
        public final static int WORD_TOTAL_DIFFERENT = 999;

        /**
         * 下一个词
         */
        public final static int WORD_NEXT = 1;

        /**
         * excel中得比第一个列词少1的ASCII码，A为65
         * 用于计算多一个两列间隔，如AA比A多（65-64） * 23
         */
        public final static int WORD_BASE = 64;

        /**
         * 一个字母代表的最多列数（26个英文字母）
         */
        public final static int WORD_SIZE = 26;

        /**
         * ASCII码为64的，用于计算列表首列为空时，第一个有数值的列与前面列空格多少
         */
        public final static String ASCII_64 = "@";

    }

    /**
     * 人员状态枚举类
     */
    @Getter
    public enum userStatus {

        /**
         * 工作状态，离职
         */
        DESERT_JOB((byte) 0, "离职"),
        /**
         * 工作状态，在职
         */
        ON_THE_JOB((byte) 1, "在职");

        private final Byte code;

        private final String value;

        userStatus(Byte code, String value) {
            this.code = code;
            this.value = value;
        }

        public static String getValueByCode(byte code) {
            for (userStatus user : userStatus.values()) {
                if (user.getCode().equals(code)) {
                    return user.getValue();
                }
            }
            return null;
        }
    }

    /**
     * excel用xml读取的标识常量
     */
    public static class ExcelToXml {

        /**
         * excel用xml读取中代表单元格的标识
         */
        public static final String XML_CELL = "c";

        /**
         * 数据格式为String标志
         */
        public static final String XML_CELL_TYPE_STRING = "s";

        /**
         * 存储excel单元格值的标识
         */
        public static final String XML_CELL_VALUE = "v";

        /**
         * 存储excel单元格值的标识
         */
        public static final String XML_CELL_VALUE_T = "t";

        /**
         * 单元格的名称（如：A1）
         */
        public static final String XML_CELL_NAME = "r";

        /**
         * 存储excel中行的标识
         */
        public static final String XML_ROW = "row";

        /**
         * 空字符串
         */
        public static final String EMPTY = "";

    }

    /**
     * 响应枚举类
     */
    @Getter
    public enum ResponseMSG {

        SUCCESS("0", "操作成功"),
        ERROR("1", "内部错误");

        private final String code;

        private final String msg;


        ResponseMSG(String code, String msg) {
            this.code = code;
            this.msg = msg;
        }
    }

    /**
     * 数字0
     */
    public static final int NUM_0 = 0;

    /**
     * 一天的毫秒数
     */
    public static final Long DAY_MILLISECOND = 24 * 60 * 60 * 1000L;

    /**
     * 东八区相差的时间毫秒数
     */
    public static final Long EAST_EIGHT_MILLISECOND = 8 * 60 * 60 * 1000L;

    /**
     * 城市常量类
     */
    public static class City {
        /**
         * 城市等级——省
         */
        public static final int CITY_LEVEL_PROVINCE = 1;
        /**
         * 城市等级——市
         */
        public static final int CITY_LEVEL_CITY = 2;
        /**
         * 城市等级——县（区）
         */
        public static final int CITY_LEVEL_COUNTRY = 3;

        /**
         * 城市代码中，每个等级占有位置
         */
        public static final int EVERY_LEVEL_CODE = 2;

    }

    /**
     * 否定，代码
     */
    public static final int FALSE_CODE = 0;

    /**
     * 肯定，代码
     */
    public static final int TRUE_CODE = 1;

    /**
     * BigDecimal 大于
     */
    public static final int BIGDECIMAL_POSITIVE = 1;

}
