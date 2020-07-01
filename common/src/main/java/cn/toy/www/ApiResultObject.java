package cn.toy.www;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * @author Created by chenhongyu on 12/06/2018.
 * 返回值定义的通用类
 */
public class ApiResultObject<T> {

    /**
     * 返回成功
     */
    public ApiResultObject() {
        ApiResultObject.createSuccessResult(null);
    }

    /**
     * 成功返回结果,默认Message
     * @param data
     * @param <T>
     * @return
     */
    public static <T extends Object>T createSuccessResult(Object data) {
        return create(COMMON_SUCCESS_CODE, data, "请求成功!");
    }

    /**
     * 成功返回结果
     * @param data
     * @param message
     * @param <T>
     * @return
     */
    public static <T extends Object>T createSuccessResult(Object data, String message) {
        return create(COMMON_SUCCESS_CODE, data, message);
    }

    /**
     * 输入错误信息返回接口错误，错误码为1
     * @param message
     * @param <T>
     * @return
     */
    public static <T extends Object>T createDefaultCodeErrorResult(String message){
        return createErrorResult(COMMON_ERROR_CODE, message);
    }

    /**
     * 输入错误信息返回接口错误，错误码为1
     * @param data
     * @param message
     * @param <T>
     * @return
     */
    public static <T extends Object>T createDefaultCodeErrorResult(Object data ,String message){
        return ApiResultObject.create(COMMON_ERROR_CODE, data, message);
    }

    /**
     * 输入默认码和message信息返回接口错误对象
     * @param code
     * @param message
     * @param <T>
     * @return
     */
    public static <T extends Object>T createErrorResult(String code, String message){
        return ApiResultObject.create(code, null, message);
    }

    /**
     * 从3个参数创建返回对象
     * @param code
     * @param data
     * @param message
     * @param <T>
     * @return
     */
    public static <T extends Object>T create(String code,
                                             Object data, String message) {
        return (T) ResultObject.getInstance().setCode(code).setData(data).setMsg(message);
    }

    /**
     * 从一个默认对象创建返回值对象
     * @param jsonObject
     * @param <T>
     * @return
     */
    public static <T extends Object>T create(JSONObject jsonObject) {
        ResultObject resultObject = ResultObject.getInstance();

        if (jsonObject == null) {
            return (T)resultObject;
        }

        resultObject.setCode(jsonObject.getString("code")).
                     setData(jsonObject.get("data")).
                     setMsg(jsonObject.getString("message"));

        return (T)resultObject;
    }

    /**
     * 返回JSONObject的封装方法
     * @param code
     * @param data
     * @param message
     * @return
     */
    public static JSONObject instanceJSONObject(String code,
                                                Object data, String message) {
        JSONObject json = new JSONObject();
        json.put("code", code);
        json.put("data", data);
        json.put("message", message);
        return json;
    }

    /**
     * 结果是否是请求成功
     * @param resultObject
     * @return
     */
    public static boolean resultIsSuccess(ResultObject resultObject) {
        if (resultObject != null) {
            if (resultObject.getCode() != null) {
                if (resultObject.getCode().equals(COMMON_SUCCESS_CODE)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 处理Json相关的转换
     */
    public static class Json{

        /**
         * 判断是否是正常的json字符串
         * @
         * @param jsonStr
         * @return
         */
        public static Boolean strIsValidJson(String jsonStr) {
            try{
                strToJsonObject(jsonStr);
                return true;
            } catch (Exception err) {
                return false;
            }
        }

        /**
         * 字符串转化为对象集合
         * @param jsonStr
         * @param clazz
         * @param <T>
         * @return
         */
        public static <T extends Serializable> List<T>  strToModelList(String jsonStr, Class<T> clazz) {
            if (Objects.isNull(jsonStr)) {
                return null;
            }
            return JSONObject.parseArray(jsonStr, clazz);
        }

        /**
         * 字符串转化为对象
         * @param jsonStr
         * @param <T>
         * @return
         */
        public static <T extends Serializable> T strToModel(String jsonStr, Class<T> clazz) {
            if (Objects.isNull(jsonStr)) {
                return null;
            }
            return JSONObject.parseObject(jsonStr, clazz);
        }

        /**
         * 字符串转化为json格式
         * @param jsonStr
         * @return
         */
        private static JSONObject strToJsonObject(String jsonStr) {
            if (Objects.isNull(jsonStr)) {
                return null;
            }
            return JSONObject.parseObject(jsonStr);
        }

        /**
         * 字符串转化为jsonArray
         * @param jsonStr
         * @return
         */
        private static JSONArray strToJsonArray(String jsonStr) {
            if (Objects.isNull(jsonStr)) {
                return null;
            }
            return JSONObject.parseArray(jsonStr);
        }

        /**
         * json转化为字符串
         * @param object
         * @return
         */
        public static String jsonObjectToStr(Object object) {
            if (Objects.isNull(object)) {
                return null;
            }
            return JSONObject.toJSONString(object);
        }

        /**
         * 获取实例
         * @return
         */
        public static Json getInstance() {

            Json json = new Json();
            return json;
        }

        private Json(){};
    }

    /**
     * 返回结果包装对象
     * @param <T>
     */
    @Getter
    public static class ResultObject<T>{


        @ApiModelProperty(value="返回状态码")
        private String code;

        @ApiModelProperty(value="返回数据实体")
        private T data;

        @ApiModelProperty(value="返回消息")
        private String msg;

        public static ResultObject getInstance() {
            ResultObject resultObject = new ResultObject();
            resultObject.code = COMMON_ERROR_CODE;
            resultObject.data = null;
            resultObject.msg = "调用内部错误";
            return resultObject;
        }

        private ResultObject(){};

        public ResultObject setCode(String code) {
            this.code = code;
            return this;
        }

        public ResultObject setData(T data) {
            this.data = data;
            return this;
        }

        public ResultObject setMsg(String msg) {
            this.msg = msg;
            return this;
        }
    }

    public static final String COMMON_SUCCESS_CODE = String.valueOf(ErrorCode.OK.getCode());
    public static final String COMMON_ERROR_CODE = String.valueOf(ErrorCode.DEFAULT_FAIL.getCode());
}
