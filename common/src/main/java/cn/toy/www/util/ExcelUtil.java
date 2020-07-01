package cn.toy.www.util;

import cn.toy.www.Constant;
import cn.toy.www.excel.model.Title;
import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @version 1.0.0
 * @Author hsl
 * @Description
 * @Date: 17:47 2018/7/16
 */
public class ExcelUtil {

    /**
     * 将配置文件中的表格样式参数转换为导出需要的样式参数
     * @param excelTitle
     * @param excelSize
     * @param excelField
     * @return
     */
    public static List<Title> getExcelDownLoadStyle(String excelTitle, String excelSize, String excelField){
        if (Objects.equals(null, excelTitle) || Objects.equals(null, excelSize)
                || Objects.equals(null, excelField)){
            return null;
        }
        String[] titles = excelTitle.split(",");
        String[] sizes = excelSize.split(",");
        String[] fields = excelField.split(",");
        if(titles.length == sizes.length && titles.length == fields.length && titles.length != Constant.Number.NUM_0){
            List<Title> titleList = new ArrayList<>();
            for (int i = 0; i< titles.length; i++){
                Title title = new Title();
                title.setTitleName(titles[i].trim());
                title.setWidth(Integer.valueOf(sizes[i]));
                title.setField(fields[i].trim());
                titleList.add(title);
            }
            return titleList;
        }
        return null;
    }

    /**
     * 导出excel的共用方法
     * @param response
     * @param wb
     * @param fileName
     */
    public static void downLoadExcel(HttpServletResponse response, Workbook wb, String fileName){
        response.setContentType("application/vnd.ms-excel");
        OutputStream outputStream = null;
        try {
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("UTF-8"),"iso-8859-1"));
            outputStream = response.getOutputStream();
            wb.write(outputStream);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("系统异常");
        } finally {
            try {
                outputStream.flush();
                outputStream.close();
                wb.close();
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("系统异常");
            }
        }
    }
}
