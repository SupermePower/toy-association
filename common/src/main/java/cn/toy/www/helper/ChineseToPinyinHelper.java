package cn.toy.www.helper;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.commons.lang3.StringUtils;

/**
 * 汉字拼音转化
 *
 * @author fly
 */
public class ChineseToPinyinHelper {

    /**
     * 格式化参数
     */
    private static HanyuPinyinOutputFormat outputFormat = new HanyuPinyinOutputFormat();

    static {
        outputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        outputFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
    }

    /**
     * 得到字符串首字的首个大写拼音
     *
     * @param str 汉字
     * @return 拼音首字母
     */
    public static String chineseToPinyin(String str) {
        if (StringUtils.isEmpty(str)) {
            return "";
        }
        StringBuilder initials = new StringBuilder();
        try {
            String pinYinStr = PinyinHelper.toHanYuPinyinString(str, outputFormat, "", true);
            initials.append(pinYinStr.substring(0, 1).toUpperCase());
        } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
            badHanyuPinyinOutputFormatCombination.printStackTrace();
        }
        return initials.toString();
    }
}
