package cn.toy.www.excel;

import cn.toy.www.excel.model.Title;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * 导出excel的工具类
 * @author hsl
 * 2018年7月10日
 * version = 1.0.0
 */
public class ExportExcelUtil {
	
	protected CellStyle titleCellStyle;
	/**
	 * 单数行的样式
	 */
	protected CellStyle singularCellStyle;
	/**
	 * 双数行的样式
	 */
	protected CellStyle evenCellStyle;

	private int pageSize = 10000;
	
	/**
	 * 初始化表格的样式
	 * @param wb
	 */
	public void initCellStyle(Workbook wb){
		titleCellStyle = getTitleStyle(wb);
		singularCellStyle = getCommonStyle(wb);
		evenCellStyle = getCommonStyle(wb);
		// 填充颜色
        evenCellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		// 设置填充格式
        evenCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    }

    public Workbook downExcel(List<Title> titleList, List<? extends Object> contentList) throws Exception{
        Workbook wb = new SXSSFWorkbook();
        initCellStyle(wb);
		//当数据为空时，返回空表格
		if(contentList.size() <= 0){
			Sheet sheet = wb.createSheet("第1页");
			setTitle(sheet, titleList);
			return wb;
		}
		//每1W条数据为一个sheet页
		for(int i = 1 ; i <= Math.ceil(contentList.size()/Double.valueOf(pageSize)); i++){
			Sheet sheet = wb.createSheet("第" + i + "页");
			setTitle(sheet, titleList);
			setContent(sheet, contentList.subList((i - 1) * pageSize, 
					(i * pageSize) <= contentList.size() ? (i * pageSize) : contentList.size())
					, titleList, i);
			//冻结第一行（标题）
			sheet.createFreezePane(0, 1);
		}
		return wb;
	} 
	
	/**
	 * 设置标题
	 * @param sheet
	 * @param titleList
	 */
	public void setTitle(Sheet sheet, List<Title> titleList){
		Row row = sheet.createRow(0);
		row.setHeightInPoints(25);
		for(int i = 0 ; i < titleList.size(); i++){
			sheet.setColumnWidth(i, titleList.get(i).getWidth());
			Cell cell = row.createCell(i);
			cell.setCellStyle(this.titleCellStyle);
			cell.setCellValue(titleList.get(i).getTitleName());
		}
	}

	/**
	 * 设置内容
	 * @param sheet
	 * @param contentList
	 * @param titleList
	 * @param sheetNumber  页数
	 * @throws Exception
	 */
	public void setContent(Sheet sheet, List<? extends Object> contentList, List<Title> titleList,
						   int sheetNumber) throws Exception{
		for(int i = 0 ; i < contentList.size(); i++){
			Object data = contentList.get(i);
			Row row = sheet.createRow(i+1);
			CellStyle cellStyle;
			if(i%2 == 0){
				cellStyle = singularCellStyle;
			}else{
				cellStyle = evenCellStyle;
			}
			//设置序号列
			Cell sCell = row.createCell(0);
			sCell.setCellStyle(cellStyle);
			sCell.setCellValue((sheetNumber - 1) * pageSize + i + 1);
			for(int j = 1 ; j < titleList.size(); j++){
				Field field = data.getClass().getDeclaredField(titleList.get(j).getField());
				Cell cell = row.createCell(j);
				cell.setCellStyle(cellStyle);
				field.setAccessible(true);
				Object value = field.get(data);
				if(value == null){
					cell.setCellValue("");
				}else if(value instanceof Boolean){
					cell.setCellValue((Boolean)value);
				}else if(value instanceof Calendar){
					cell.setCellValue((Calendar)value);
				}else if(value instanceof Date){
					cell.setCellValue((Date)value);
				}else if(value instanceof Number){
					cell.setCellValue(((Number)value).doubleValue());
				}else if(value instanceof RichTextString){
					cell.setCellValue((RichTextString)value);
				}else if(value instanceof String){
					cell.setCellValue((String)value);
				}else{
					throw new Exception(data.getClass() + "对象的属性:"+field.getName() + " 类型为：" +field.getType() + " 值：" + value
					         + "不在列表可以到处的属性{Boolean,Calendar,Date,Double,RichTextString,String}中，请重新定义到处对象。");
				}
			}
		}
	}
	
	/**
	 * 获取标题的样式
	 * @param wb
	 * @return
	 */
	public CellStyle getTitleStyle(Workbook wb) {
		CellStyle cellStyle = wb.createCellStyle();
		// 设置单元格水平方向的对齐方式
		cellStyle.setAlignment(HorizontalAlignment.CENTER);
		// 设置单元格垂直方向对齐方式
		cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		// 填充颜色
		cellStyle.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
		// 设置填充格式
		cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cellStyle.setBorderBottom(BorderStyle.THIN);
		cellStyle.setBottomBorderColor(IndexedColors.GREY_80_PERCENT.index);
		cellStyle.setBorderTop(BorderStyle.THIN);
		cellStyle.setTopBorderColor(IndexedColors.GREY_80_PERCENT.index);
		cellStyle.setBorderLeft(BorderStyle.THIN);
		cellStyle.setLeftBorderColor(IndexedColors.GREY_80_PERCENT.index);
		cellStyle.setBorderRight(BorderStyle.THIN);
		cellStyle.setRightBorderColor(IndexedColors.GREY_80_PERCENT.index);
		// 设置可换行
		cellStyle.setWrapText(true);
		Font font = wb.createFont();
		font.setColor(IndexedColors.WHITE.index);
		font.setFontHeightInPoints((short) 10);
		cellStyle.setFont(font);
		return cellStyle;
	}
	
	/**
	 * 通用样式
	 * @param wb
	 * @return
	 */
	public CellStyle getCommonStyle(Workbook wb){
		
		CellStyle cellStyle = wb.createCellStyle();
		// 设置单元格水平方向的对齐方式
		cellStyle.setAlignment(HorizontalAlignment.CENTER);;
		// 设置单元格垂直方向对齐方式
		cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		cellStyle.setBorderBottom(BorderStyle.THIN);
		cellStyle.setBottomBorderColor(IndexedColors.BLACK.index);
		cellStyle.setBorderTop(BorderStyle.THIN);
		cellStyle.setTopBorderColor(IndexedColors.BLACK.index);
		cellStyle.setBorderLeft(BorderStyle.THIN);
		cellStyle.setLeftBorderColor(IndexedColors.BLACK.index);
		cellStyle.setBorderRight(BorderStyle.THIN);
		cellStyle.setRightBorderColor(IndexedColors.BLACK.index);
		Font font = wb.createFont();
		font.setColor(IndexedColors.BLACK.index);
		font.setFontHeightInPoints((short)9);
		cellStyle.setFont(font);
		return cellStyle;
	}
	
}
