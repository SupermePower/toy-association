package cn.toy.www.util;

import com.google.common.collect.Lists;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 拼音工具类
 *
 * @author YanBingHao
 * @since 2019/7/30
 */
public class PinyinUtil {

    public static final String MATCH_CHINESS_STR_REGEX = "[\\u4E00-\\u9FA5]+";

    private static final String UPPER_SUFFIX = "_U";
    private static final String LOWER_SUFFIX = "_L";

    // 小写
    public static final HanyuPinyinCaseType DEFAULT_CASE_TYPE = HanyuPinyinCaseType.LOWERCASE;
    // 不带声调
    public static final HanyuPinyinToneType DEFAULT_TONE_TYPE = HanyuPinyinToneType.WITHOUT_TONE;

    public static final Map<String, List<String>> CHAR_PINYIN_MAP = new ConcurrentHashMap<String, List<String>>();

    /**
     * 校验中文字符拼音首字母是否包含待校验的字符串（例如：长，C）
     *
     * @param chinessChar
     * @param chkStr
     * @return
     */
    public static boolean chkCharPinyinHeaderRetain(char chinessChar, String chkStr) {
        if (StringUtils.isBlank(chkStr)) {
            return false;
        }
        try {
            List<String> headers = getCharPinyinHeaderRetain(chinessChar, true);
            return headers.contains(chkStr.toUpperCase());
        } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
            badHanyuPinyinOutputFormatCombination.printStackTrace();
            return false;
        }
    }

    /**
     * 获取字符的拼音首字母（多音字）
     *
     * @param chinessChar
     * @return
     * @throws BadHanyuPinyinOutputFormatCombination
     */
    public static List<String> getCharPinyinHeaderRetain(final char chinessChar, boolean toUpperCase) throws BadHanyuPinyinOutputFormatCombination {
        List<String> retVal = Lists.newArrayList();
        String key = chinessChar + (toUpperCase ? UPPER_SUFFIX : LOWER_SUFFIX);

        List<String> tempList = CHAR_PINYIN_MAP.get(key);
        if (CollectionUtils.isNotEmpty(tempList)) {
            return tempList;
        }
        convertToPinyinRetain(chinessChar).stream().forEach(i -> {
            retVal.add(toUpperCase ? Character.toString(i.charAt(0)).toUpperCase() : Character.toString(i.charAt(0)));
        });
        CHAR_PINYIN_MAP.put(key, retVal);
        return retVal;
    }

    /**
     * 获取字符串的首个字符的拼音首字母
     *
     * @param chinessStr
     * @return
     * @throws BadHanyuPinyinOutputFormatCombination
     */
    public static String getFirstCharPinyinHeader(String chinessStr) throws BadHanyuPinyinOutputFormatCombination {
        return Character.toString(getFirstCharPinyin(chinessStr).charAt(0));
    }

    /**
     * 获取字符串的各个拼音的开头
     *
     * @param chinessStr
     * @return
     * @throws BadHanyuPinyinOutputFormatCombination
     */
    public static String getCharsPinyinHeader(String chinessStr) throws BadHanyuPinyinOutputFormatCombination {
        if (StringUtils.isBlank(chinessStr)) {
            return null;
        }

        StringBuilder retVal = new StringBuilder();
        char[] chars = chinessStr.toCharArray();
        for (char aChar : chars) {
            if (!Character.toString(aChar).matches(MATCH_CHINESS_STR_REGEX)) {
                retVal.append(aChar);
                continue;
            }
            retVal.append(convertToPinyin(aChar).toUpperCase().charAt(0));
        }
        return retVal.toString();
    }

    /**
     * 获取第一个字符的拼音
     *
     * @param chinessStr
     * @return
     * @throws BadHanyuPinyinOutputFormatCombination
     */
    public static String getFirstCharPinyin(String chinessStr) throws BadHanyuPinyinOutputFormatCombination {
        if (StringUtils.isBlank(chinessStr)) {
            return null;
        }

        char[] chars = chinessStr.toCharArray();
        return convertToPinyin(chars[0]);
    }

    /**
     * 字符串转拼音
     *
     * @param chinessStr
     * @return
     * @throws BadHanyuPinyinOutputFormatCombination
     */
    public static String convertToPinyin(String chinessStr) throws BadHanyuPinyinOutputFormatCombination {
        if (StringUtils.isBlank(chinessStr)) {
            return null;
        }

        StringBuilder retVal = new StringBuilder();
        char[] chars = chinessStr.toCharArray();
        for (char aChar : chars) {
            if (!Character.toString(aChar).matches(MATCH_CHINESS_STR_REGEX)) {
                retVal.append(aChar);
                continue;
            }
            retVal.append(convertToPinyin(aChar).toUpperCase());
        }
        return retVal.toString();
    }

    /**
     * 汉字转拼音
     *
     * @param chinessChar 汉字
     * @return
     * @throws BadHanyuPinyinOutputFormatCombination
     */
    private static String convertToPinyin(char chinessChar) throws BadHanyuPinyinOutputFormatCombination {
        return convertToPinyin(chinessChar, null, null);
    }

    /**
     * 汉字转拼音（多音字）
     *
     * @param chinessChar 汉字
     * @return
     * @throws BadHanyuPinyinOutputFormatCombination
     */
    private static List<String> convertToPinyinRetain(char chinessChar) throws BadHanyuPinyinOutputFormatCombination {
        return convertToPinyinRetain(chinessChar, null, null);
    }

    /**
     * 汉字转拼音
     *
     * @param chinessChar
     * @param caseType
     * @param toneType
     * @return
     * @throws BadHanyuPinyinOutputFormatCombination
     */
    private static String convertToPinyin(char chinessChar, HanyuPinyinCaseType caseType, HanyuPinyinToneType toneType) throws BadHanyuPinyinOutputFormatCombination {
        HanyuPinyinOutputFormat pinyinFormat = new HanyuPinyinOutputFormat();
        pinyinFormat.setCaseType(Optional.ofNullable(caseType).orElse(DEFAULT_CASE_TYPE));
        pinyinFormat.setToneType(Optional.ofNullable(toneType).orElse(DEFAULT_TONE_TYPE));
        pinyinFormat.setVCharType(HanyuPinyinToneType.WITH_TONE_MARK.equals(toneType) ? null : HanyuPinyinVCharType.WITH_V);
        if (!Character.toString(chinessChar).matches(MATCH_CHINESS_STR_REGEX)) {
            return Character.toString(chinessChar);
        }
        return PinyinHelper.toHanYuPinyinString(Character.toString(chinessChar), pinyinFormat, "", false);
    }

    /**
     * 汉字转拼音（多音字）
     *
     * @param chinessChar
     * @param caseType
     * @param toneType
     * @return
     * @throws BadHanyuPinyinOutputFormatCombination
     */
    private static List<String> convertToPinyinRetain(char chinessChar, HanyuPinyinCaseType caseType, HanyuPinyinToneType toneType) throws BadHanyuPinyinOutputFormatCombination {
        HanyuPinyinOutputFormat pinyinFormat = new HanyuPinyinOutputFormat();
        pinyinFormat.setCaseType(Optional.ofNullable(caseType).orElse(DEFAULT_CASE_TYPE));
        pinyinFormat.setToneType(Optional.ofNullable(toneType).orElse(DEFAULT_TONE_TYPE));
        pinyinFormat.setVCharType(HanyuPinyinToneType.WITH_TONE_MARK.equals(toneType) ? null : HanyuPinyinVCharType.WITH_V);
        if (!Character.toString(chinessChar).matches(MATCH_CHINESS_STR_REGEX)) {
            return Lists.newArrayList(Character.toString(chinessChar));
        }
        return Lists.newArrayList(PinyinHelper.toHanyuPinyinStringArray(chinessChar, pinyinFormat));
    }

    public static void main(String[] args) throws Exception {
        System.out.println(getCharsPinyinHeader("东直门"));
    }

}
