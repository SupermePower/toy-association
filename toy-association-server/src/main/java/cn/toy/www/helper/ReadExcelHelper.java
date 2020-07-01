package cn.toy.www.helper;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import static org.apache.poi.ss.usermodel.CellType.NUMERIC;

/**
 * @author fly
 * @description 读取excel
 * @date 2020/4/21 23:20
 */
@Component
public class ReadExcelHelper {


    private final static String XLS = "xls";

    private final static String XLSX = "xlsx";

    /**
     * 获取工作薄对象
     *
     * @param file file
     * @return Workbook
     */
    public Workbook getWorkBook(MultipartFile file) {
        //获得文件名
        String fileName = file.getOriginalFilename();
        if (Objects.nonNull(fileName)) {
            //创建Workbook工作薄对象，表示整个excel
            Workbook workbook = null;
            try {
                //获取excel文件的io流
                InputStream is = file.getInputStream();
                //根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象
                if (fileName.endsWith(XLS)) {
                    //2003
                    workbook = new HSSFWorkbook(is);
                } else if (fileName.endsWith(XLSX)) {
                    //2007
                    workbook = new XSSFWorkbook(is);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return workbook;
        } else {
            throw new RuntimeException("获取文件对象失败");
        }
    }


    public String getCellValue(Cell cell) {
        String cellValue = "";
        if (cell == null) {
            return cellValue;
        }
        //把数字当成String来读，避免出现1读成1.0的情况
        if (cell.getCellTypeEnum() == NUMERIC) {
            cell.setCellType(CellType.STRING);
        }
        //判断数据的类型
        switch (cell.getCellTypeEnum()) {
            case NUMERIC:
                cellValue = String.valueOf(cell.getNumericCellValue());
                break;
            case STRING:
                cellValue = String.valueOf(cell.getStringCellValue()).trim();
                break;
            case BOOLEAN:
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case FORMULA:
                cellValue = String.valueOf(cell.getCellFormula());
                break;
            case BLANK:
                cellValue = "";
                break;
            case ERROR:
                cellValue = "非法字符";
                break;
            default:
                cellValue = "未知类型";
                break;
        }
        return cellValue;
    }
}
