package cn.toy.www;

import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 *  字符串压缩工具类
 * @author qimy
 */
@Slf4j
public class StringCompressionUtil {

    public static final String CHARSET_NAME_COMPRESS="ISO-8859-1";

    public static final String CHARSET_NAME_REVERT="UTF-8";

    /**
     * 字符串的压缩
     *
     * @param str 待压缩的字符串
     * @return    返回压缩后的字符串
     */
    public static synchronized String compress(String str){
        long currentTimeMillis = System.currentTimeMillis();
        if (null == str || str.length() <= 0) {
            return str;
        }
        try{
            // 创建一个新的 byte 数组输出流
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            // 使用默认缓冲区大小创建新的输出流
            GZIPOutputStream gzip = new GZIPOutputStream(out);
            // 将 b.length 个字节写入此输出流
            gzip.write(str.getBytes());
            gzip.close();
            // 使用指定的 charsetName，通过解码字节将缓冲区内容转换为字符串
            log.info("压缩字符串耗时：{}",System.currentTimeMillis()-currentTimeMillis);
            return out.toString(CHARSET_NAME_COMPRESS);
        }catch (IOException e){
            log.error(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 字符串的解压
     *
     * @param binaryStr 对字符串解压
     * @return 返回解压缩后的字符串
     */
    public static synchronized String unCompress(String binaryStr){
        long currentTimeMillis = System.currentTimeMillis();
        if (null == binaryStr || binaryStr.length() <= 0) {
            return binaryStr;
        }
        try{
            // 创建一个新的 byte 数组输出流
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            // 创建一个 ByteArrayInputStream，使用 buf 作为其缓冲区数组
            ByteArrayInputStream in = new ByteArrayInputStream(binaryStr.getBytes(CHARSET_NAME_COMPRESS));
            // 使用默认缓冲区大小创建新的输入流
            GZIPInputStream gzip = new GZIPInputStream(in);
            byte[] buffer = new byte[256];
            int n;
            // 将未压缩数据读入字节数组
            while ((n = gzip.read(buffer)) >= 0) {
                // 将指定 byte 数组中从偏移量 off 开始的 len 个字节写入此 byte数组输出流
                out.write(buffer, 0, n);
            }
            // 使用指定的 charsetName，通过解码字节将缓冲区内容转换为字符串
            log.info("解压字符串耗时：{}",System.currentTimeMillis()-currentTimeMillis);
            return out.toString(CHARSET_NAME_REVERT);
        }catch (Exception e){
            log.error(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
