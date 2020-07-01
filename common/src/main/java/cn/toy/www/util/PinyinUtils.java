package cn.toy.www.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import org.apache.logging.log4j.util.Strings;

/**
 * @Auther: zjt
 * @Date: 2019/7/29 18:16
 * @Description:字符串提取首字母
 */

public class PinyinUtils {

    /**
     * 提取第一个汉子/单词的首字母(大写)
     *
     * @param str
     * @return
     */
    public static String getFirstHeadWordChar(String str) {
        if (Strings.isBlank(str)) {
            return "";
        }
        String convert = "";
        char word = str.charAt(0);
        // 提取汉字的首字母
        convert = getFirstChar(word,convert);
        convert = string2AllTrim(convert);
        return convert.toUpperCase();
    }

    /**
     * 提取单词的首字母(大写)--共用
     *
     * @param word
     * @param convert
     * @return convert
     */
    private static String getFirstChar(char word,String convert) {
        String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
        if (pinyinArray != null) {
            convert += pinyinArray[0].charAt(0);
        }
        else {
            convert += word;
        }
        return convert;
    }

    /**
     * 提取每个汉字的首字母(大写)
     *
     * @param str
     * @return
     */
    public static String getPinYinHeadChar(String str) {
        if (Strings.isBlank(str)) {
            return "";
        }
        String convert = "";
        for (int j = 0; j < str.length(); j++) {
            char word = str.charAt(j);
            // 提取汉字的首字母
            convert = getFirstChar(word,convert);
        }
        convert = string2AllTrim(convert);
        return convert.toUpperCase();
    }

    /**
     * 去掉字符串包含的所有空格
     *
     * @param value
     * @return
     */
    public static String string2AllTrim(String value) {
        if (Strings.isBlank(value)) {
            return "";
        }
        return value.trim().replace(" ", "");
    }

}
