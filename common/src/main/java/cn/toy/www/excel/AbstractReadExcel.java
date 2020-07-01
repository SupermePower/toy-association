package cn.toy.www.excel;

import cn.toy.www.Constant;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * 读取excel抽象类
 * @author hsl
 * @version 1.0.0
 * 2018/07/12
 */
public abstract class AbstractReadExcel extends DefaultHandler{

	private SharedStringsTable sst;   
    private String lastContents;   
    private boolean nextIsString;   
  
    private int sheetIndex = 0;
    private List<String> rowlist = new ArrayList<String>();   
    private int curRow = 0;   
    private int curCol = 0;

    /**
     * 遍历表格的时候，上一个列的名称
     */
    private String lastColName = Constant.Excel.ASCII_64;

    /**
     * 当前单元格名称
     */
    private String curColName;

    /**
     * 表列的大小
     */
    private int columnSize = 0;
       
       
    /**  
     * 读取第一个工作簿的入口方法  
     * @param path  
     */  
    public void readOneSheet(String path) throws Exception {   
        OPCPackage pkg = OPCPackage.open(path);        
        XSSFReader r = new XSSFReader(pkg);   
        SharedStringsTable sst = r.getSharedStringsTable();   
               
        XMLReader parser = fetchSheetParser(sst);   
               
        InputStream sheet = r.getSheet("rId1");   
  
        InputSource sheetSource = new InputSource(sheet);   
        parser.parse(sheetSource);   
               
        sheet.close();         
    }   
       
       
    /**  
     * 读取所有工作簿的入口方法  
     * @param inputStram
     * @throws Exception 
     */  
    public void process(InputStream inputStram) throws Exception {
        OPCPackage pkg = OPCPackage.open(inputStram);
        XSSFReader r = new XSSFReader(pkg);   
        SharedStringsTable sst = r.getSharedStringsTable();   
  
        XMLReader parser = fetchSheetParser(sst);   

        Iterator<InputStream> sheets = r.getSheetsData();
        while (sheets.hasNext()) {   
            curRow = 0;   
            sheetIndex++;   
            InputStream sheet = sheets.next();
            InputSource sheetSource = new InputSource(sheet);   
            parser.parse(sheetSource);   
            sheet.close();   
        }   
    }   
       
    /**  
     * 该方法自动被调用，每读一行调用一次，在方法中写自己的业务逻辑即可 
     * @param sheetIndex 工作簿序号 
     * @param curRow 处理到第几行 
     * @param rowList 当前数据行的数据集合
     * @return
     */  
    public abstract Object optRow(int sheetIndex, int curRow, List<String> rowList);
       
       
    public XMLReader fetchSheetParser(SharedStringsTable sst) throws SAXException {   
        XMLReader parser = XMLReaderFactory   
                .createXMLReader("org.apache.xerces.parsers.SAXParser");   
        this.sst = sst;   
        parser.setContentHandler(this);   
        return parser;   
    }

    @Override
    public void startElement(String uri, String localName, String name,   
            Attributes attributes) throws SAXException {   
        // c => 单元格  
        if (Constant.ExcelToXml.XML_CELL.equals(name)) {
            // 判断两个列之间是否为连续的
            curColName = attributes.getValue(Constant.ExcelToXml.XML_CELL_NAME);
            // 如果下一个元素是 SST 的索引，则将nextIsString标记为true
            String cellType = attributes.getValue(Constant.ExcelToXml.XML_CELL_VALUE_T);
            if (cellType != null && cellType.equals(Constant.ExcelToXml.XML_CELL_TYPE_STRING)) {
                nextIsString = true;   
            } else {   
                nextIsString = false;   
            }   
        }   
        // 置空   
        lastContents = "";   
    }

    @Override
    public void endElement(String uri, String localName, String name)   
            throws SAXException {   
        // 根据SST的索引值的到单元格的真正要存储的字符串  
        // 这时characters()方法可能会被调用多次  
        if (nextIsString) {   
            try {   
                int idx = Integer.parseInt(lastContents);   
                lastContents = new XSSFRichTextString(sst.getEntryAt(idx))   
                        .toString();   
            } catch (Exception e) {   
  
            }   
        }   
  
        // v => 单元格的值，如果单元格是字符串则v标签的值为该字符串在SST中的索引  
        // 将单元格内容加入rowlist中，在这之前先去掉字符串前后的空白符  
        if (Constant.ExcelToXml.XML_CELL_VALUE.equals(name) || Constant.ExcelToXml.XML_CELL_VALUE_T.equals(name)) {
            //补足空格信息
            int dif = getDistanceByTwoWord(lastColName, curColName);
            if (dif > Constant.Excel.WORD_NEXT){
                for (int i = 1; i < dif; i++){
                    rowlist.add(curCol,null);
                    curCol++;
                }
            }
            lastColName = curColName;

            String value = lastContents.trim();   
            value = Constant.ExcelToXml.EMPTY.equals(value) ? " " : value;
            rowlist.add(curCol, value);
            curCol++;
        } else {   
            // 如果标签名称为 row ，这说明已到行尾，调用 optRows() 方法  
            if (Constant.ExcelToXml.XML_ROW.equals(name)) {
                //补足空格
                columnSize = columnSize >= rowlist.size() ? columnSize : rowlist.size();
                if(rowlist.size() < columnSize){
                    int size = rowlist.size();
                    for(int i = 0 ; i < (columnSize - size); i++){
                        rowlist.add(null);
                    }
                }
                optRow(sheetIndex, curRow, rowlist);
                rowlist.clear();
                lastColName = Constant.Excel.ASCII_64;
                curRow++;   
                curCol = 0;   
            }   
        }   
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        // 得到单元格内容的值
        lastContents += new String(ch, start, length);
    }

    /**
     * 获取表格word2列和word1列的差值（如：B列和A列的差值为1，相同返回0，不一致太多返回999），
     * 用于将列表中的未填空格赋值null,要求word2与word1同一行且word2是在word1之后列，否则返回999
     * @param word1
     * @param word2
     * @return
     */
    private int getDistanceByTwoWord(String word1, String word2){
        if (Objects.equals(null, word1) || Objects.equals(null, word2)
                || Objects.equals("", word1.trim()) || Objects.equals("" ,word2.trim())){
            return Constant.Excel.WORD_TOTAL_DIFFERENT;
        }

        // 删除列名中的数字，然后转换成字符数组
        char[] cWord1 = word1.replaceAll("\\d+","").toCharArray();
        char[] cWord2 = word2.replaceAll("\\d+","").toCharArray();
        int different = Constant.Excel.WORD_SAME;
        for (int i = 1 ; i <= cWord2.length; i++){
             if (cWord1.length - i >= Constant.Number.NUM_0){
                different = different + ((int)cWord2[cWord2.length - i] - (int)cWord1[cWord1.length - i])
                         * ((int)Math.pow(Constant.Excel.WORD_SIZE, i - Constant.Number.NUM_1));
            } else if (cWord2.length - i >= Constant.Number.NUM_0){
                 different = different + ((int)cWord2[cWord2.length - i] - Constant.Excel.WORD_BASE)
                         * ((int)Math.pow(Constant.Excel.WORD_SIZE, i - Constant.Number.NUM_1));
             }
        }
        return different;
    }

}
