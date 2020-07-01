package cn.toy.www.util.ossutil;

import cn.toy.www.security.TokenUser;
import cn.toy.www.util.DateUtil;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;


/**
 * @author: zhaobingyu
 * @Date: 6:06 PM 04/06/2018
 */
@Slf4j
public final class OssUploadUtil {

    public static void main(String[] args) {
    }

    /**
     * web直传获取 accessid, policy, signature
     * https://help.aliyun.com/document_detail/91868.html?spm=a2c4g.11186623.2.15.16074382NK0MfG#concept-ahk-rfz-2fb
     * @param token token
     * @param dir  文件夹名
     * @param fileName 文件名
     * @param callbackUrl  回调地址
     * @return
     */
    public static Map<String, String> webUploadBackTokenInfo(TokenUser token, String dir, String fileName, String callbackUrl) throws FileNameIsNullException {

        if (Objects.isNull(dir)) {
            dir = "";
        }

        if (Objects.isNull(fileName) || StringUtils.isBlank(fileName) || !fileName.contains(".")) {
            throw new FileNameIsNullException();
        }

        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        log.info("上传的后缀名为：" + suffixName);

        String uuid = UUID.randomUUID().toString().replaceAll("-", "");

        OSSClient client = new OSSClient(END_POINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        Map<String, String> respMap = new LinkedHashMap<>();

        try {
            final long expireTime = 300;
            long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
            Date expiration = new Date(expireEndTime);
            PolicyConditions policyConds = new PolicyConditions();
            policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
            policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);

            String postPolicy = client.generatePostPolicy(expiration, policyConds);
            byte[] binaryData = postPolicy.getBytes("utf-8");
            String encodedPolicy = BinaryUtil.toBase64String(binaryData);
            String postSignature = client.calculatePostSignature(postPolicy);

            respMap.put("accessid", ACCESS_KEY_ID);
            respMap.put("policy", encodedPolicy);
            respMap.put("signature", postSignature);
            respMap.put("dir", dir);
            respMap.put("host", ACCESS_URL);
            respMap.put("expire", String.valueOf(expireEndTime / 1000));
            //respMap.put("expire", formatISO8601Date(expiration));
            respMap.put("filename", uuid + suffixName);

            JSONObject jasonCallback = new JSONObject();
            jasonCallback.put("callbackUrl", callbackUrl);
            //如果此处修改，回调的位置获取callbackbody参数也需要对应修改
            jasonCallback.put("callbackBody",
                    "filename=${object}&size=${size}&mimeType=${mimeType}&height=${imageInfo.height}&width=${imageInfo.width}&format=${imageInfo.format}&userId=" +
                            token.getId()+"&dir="+dir+"&sourceFilename="+ StringUtils.replace(fileName, "&", "_"));
            jasonCallback.put("callbackBodyType", "application/x-www-form-urlencoded");

            String base64CallbackBody = BinaryUtil.toBase64String(jasonCallback.toString().getBytes());
            respMap.put("callback", base64CallbackBody);

        } catch (Exception e) {
            log.error("oss直传获取签名异常 -> " + e.getMessage());
            respMap.put("exception", e.getMessage());
        }

        return respMap;
    }

    /**
     * 上传二进制文件
     * @param fileInputStream
     * @param fileName
     * @return
     */
    public static String uploadByStream (InputStream fileInputStream, String fileName) {
        OSSClient ossClient = new OSSClient(END_POINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        ossClient.putObject(BUCKET_NAME, uuid + "-" + fileName, fileInputStream);
        ossClient.shutdown();
        return uuid;
    }

    /**
     * 根据文件上传
     *
     * @param file
     * @param uploadFileName
     * @return
     * @throws FileNotFoundException
     */
    public static String updateByFile(File file, String uploadFileName) throws FileNotFoundException {
        return updateByInputStream(new FileInputStream(file), uploadFileName);
    }

    /**
     * 根据输入流上传
     *
     * @param inputStream
     * @param uploadFileName
     * @return
     */
    public static String updateByInputStream(InputStream inputStream, String uploadFileName) {
        String uploadPath = buildUploadPath(uploadFileName);
        OSSClient ossClient = new OSSClient(END_POINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        ossClient.putObject(BUCKET_NAME, uploadPath, inputStream);
        ossClient.shutdown();
        return ACCESS_URL + uploadPath;
    }

    private static String buildUploadPath(String uploadFileName) {
        return DateUtil.format(DateUtil.YYYYMMDD) + "/" + uploadFileName;
    }

    /**
     * 下载文件
     * @param downloadFilePath  目标文件路径
     * @param fileName oss 文件名称
     */
    public static void downloadFile(String downloadFilePath, String fileName) {
        OSSClient  ossClient = null;
        try {
            File localFile = new File(downloadFilePath);
            if (!localFile.exists()) {
                if (!localFile.getParentFile().exists()) {
                    localFile.getParentFile().mkdirs();
                }
                localFile.createNewFile();
                ossClient = new OSSClient(END_POINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
                ossClient.getObject(new GetObjectRequest(BUCKET_NAME, fileName), localFile);
            }
        } catch (Exception e) {
            throw new RuntimeException("oss 下载文件失败 : " + e.getMessage());
        } finally {
            if (Objects.nonNull(ossClient)) {
                ossClient.shutdown();
            }
        }
    }

    public static String uploadByFile(MultipartFile file) {

        if(file.isEmpty()){
            throw new RuntimeException("文件为空");
        }

        String fileName = file.getOriginalFilename()  ;
        log.info("上传的文件名为：" + fileName);

        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        log.info("上传的后缀名为：" + suffixName);

        String uuid = UUID.randomUUID().toString().replaceAll("-", "");


        OSSClient ossClient = new OSSClient(END_POINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);

        ossClient.putObject(BUCKET_NAME, uuid, new File(file.getOriginalFilename()));

        ossClient.shutdown();
        return uuid;
    }

    private static String END_POINT = "oss-cn-beijing.aliyuncs.com";

    private static String ACCESS_KEY_ID = "LTAI4FvBQAEb15eYWQDqSBvJ";

    private static String ACCESS_KEY_SECRET = "F9zpxqHMyuD7h9UKzN9eC6rgSJvIWx";

    private static String BUCKET_NAME = "yundou-logo";

    public static String ACCESS_URL = "https://yundou-logo.oss-cn-beijing.aliyuncs.com/";

    private OssUploadUtil() {}
}


