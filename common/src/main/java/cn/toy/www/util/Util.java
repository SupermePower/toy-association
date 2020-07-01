package cn.toy.www.util;

import cn.toy.www.Constant;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * 普通工具类
 * @author hsl
 * @date 2018-10-15 16:57
 */
public class Util {

    /**
     * 生成id，时间戳+三位随机数
     * @return
     */
    public static Long getId(){
        String code = String.valueOf(System.currentTimeMillis());
        //取三位随机数
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int random = (int)(Math.random()*900)+100;
        code = code + String.format("%03d", random);
        return Long.valueOf(code);
        /*String numberPattern = "[^0-9]";
        String id = UUIDHelper.getUUID();
        Pattern pattern = Pattern.compile(numberPattern);
        Matcher matcher = pattern.matcher(id);
        String sId = matcher.replaceAll("").trim();
        if(sId.length() > 15){
            sId = sId.substring(0,15);
        }

        return Long.valueOf(sId);*/
    }

    /**
     * 判断是否分页
     * @param startPage 开始页码
     * @param pageSize 每页数据量
     * @return 是否分页
     */
    public static boolean isPageHelper(Integer startPage, Integer pageSize){
        if (Objects.nonNull(startPage)
                && Objects.nonNull(pageSize)
                &&startPage >= Constant.NUM_0
                && pageSize > Constant.NUM_0){
            return true;
        }
        return false;
    }

    /**
     * 判断列表是否为空
     * @param list 列表
     * @return 是否为空
     */
    public static boolean isEmptyList(List list){
        return Objects.isNull(list) || list.size() == Constant.Number.NUM_0;
    }

    /**
     * 数字有BigDecimal格式转换成Double
     * @param number 数字
     * @return 返回数字
     */
    public static Double bigDecimalToDouble(BigDecimal number){
        if(Objects.isNull(number)){
            return null;
        }
        return number.doubleValue();
    }
}
