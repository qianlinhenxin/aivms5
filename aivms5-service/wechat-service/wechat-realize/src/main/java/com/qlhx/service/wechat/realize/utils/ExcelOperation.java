//package com.qlhx.service.wechat.realize.utils;
//
//
//import com.qlhx.model.VisitorRecordBo;
//import com.qlhx.service.wechat.realize.model.VisitorRecordBo;
//import org.apache.poi.hssf.usermodel.*;
//import org.apache.poi.ss.usermodel.DateUtil;
//import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.ss.util.NumberToTextConverter;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//import javax.servlet.http.HttpServletResponse;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.InputStream;
//import java.lang.reflect.Field;
//import java.lang.reflect.Method;
//import java.math.BigDecimal;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.*;
//
///**
// * Created by rongcan on 17/1/11.
// */
//public class ExcelOperation<T> {
//
//    private final static String excel2003L = ".xls";    //2003- 版本的excel
//    private final static String excel2007U = ".xlsx";   //2007+ 版本的excel
//
//    public static String getSystemFileCharset() {
//        Properties pro = System.getProperties();
//        return pro.getProperty("file.encoding");
//    }
//
//    /**
//     * 导出Excel
//     */
//    public String exportExcel(HttpServletResponse response, Map<String, String> hreads, List<T> excelData) throws Exception {
//
//
//        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期
//
//        String filePath = UtilPath.getRootPath() + "zipDic/";
//        File dir = new File(filePath);
//        if (!dir.exists()) {
//            dir.mkdir();
//        }
//        String fileName = df.format(new Date()) + ".xls";
//        try {
//            ExcelOperation<VisitorRecordBo> excelOperation = new ExcelOperation<>();
//            String fileEncoding = getSystemFileCharset();
//
//            int rowNum = 0;
//            int CellNum = 0;
//            // 第一步，创建一个webbook，对应一个Excel文件
//            HSSFWorkbook wb = new HSSFWorkbook();
//            // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
//            HSSFSheet sheet = wb.createSheet("Sheet1");
//            // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
//            HSSFRow row = sheet.createRow(rowNum);
//            // 第四步，创建单元格，并设置值表头 设置表头居中
//            HSSFCellStyle style = wb.createCellStyle();
//            style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
//
//            for (String h : hreads.values()) {
//                HSSFCell cell = row.createCell(CellNum);
//
//                cell.setCellValue(new String(h.toString().getBytes(), fileEncoding));
//                cell.setCellStyle(style);
//                CellNum++;
//            }
//            rowNum++;
//            Field[] fields = null;
//            if (excelData.size() > 0) {
//                Class<?> class1 = excelData.get(0).getClass();
//                fields = class1.getDeclaredFields();
//                int copyStart = fields.length;
//                Field[] supperFields = class1.getSuperclass().getDeclaredFields();
//                fields = Arrays.copyOf(fields, fields.length + supperFields.length);
//                System.arraycopy(supperFields, 0, fields, copyStart, supperFields.length);
//            }
//            // 第五步，写入实体数据
//            for (T model : excelData) {
//                CellNum = 0;
//                row = sheet.createRow(rowNum);
//                for (String key : hreads.keySet()) {
//                    String name = key.substring(0, 1).toUpperCase() + key.substring(1); // 将属性的首字符大写，方便构造get，set方法
//                    String type = "";
//                    if (fields != null) {
//                        for (Field field : fields) {
//                            if (field.getName().equals(key)) {
//                                type = field.getType().toString();
//                            }
//                        }
//                    }
//
//                    String CellValue = "";
//                    if (type.equals("class java.lang.Integer")) {
//                        Method m = model.getClass().getMethod("get" + name);
//                        Integer value = (Integer) m.invoke(model);
//
//                        if (value != null) {
//                            CellValue = value.toString();
//                        }
//                    }
//                    if (type.equals("class java.lang.Short")) {
//                        Method m = model.getClass().getMethod("get" + name);
//                        Short value = (Short) m.invoke(model);
//                        if (value != null) {
//                            CellValue = value.toString();
//                        }
//                    }
//                    if (type.equals("class java.math.BigDecimal")) {
//                        Method m = model.getClass().getMethod("get" + name);
//                        BigDecimal value = (BigDecimal) m.invoke(model);
//                        if (value != null) {
//                            CellValue = value.toString();
//                        }
//                    }
//
//                    if (type.equals("class java.lang.Double")) {
//                        Method m = model.getClass().getMethod("get" + name);
//                        Double value = (Double) m.invoke(model);
//                        if (value != null) {
//                            System.out.println("attribute value:" + value);
//                            CellValue = value.toString();
//                        }
//                    }
//                    if (type.equals("class java.lang.Boolean")) {
//                        Method m = model.getClass().getMethod("get" + name);
//                        Boolean value = (Boolean) m.invoke(model);
//                        if (value != null) {
//                            System.out.println("attribute value:" + value);
//                            CellValue = value.toString();
//                        }
//                    }
//                    if (type.equals("class java.util.Date")) {
//                        Method m = model.getClass().getMethod("get" + name);
//                        Date value = (Date) m.invoke(model);
//                        if (value != null) {
//                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                            CellValue = dateFormat.format(value).toString();
//                        }
//                    }
//                    if (type.equals("class java.lang.String")) {
//                        Method m = model.getClass().getMethod("get" + name);
//                        String value = (String) m.invoke(model);
//                        if (value != null) {
//                            CellValue = value;
//                        }
//                    }
//                    row.createCell(CellNum).setCellValue(CellValue);
//                    sheet.autoSizeColumn(CellNum);
//                    CellNum++;
//                }
//                rowNum++;
//            }
//            FileOutputStream fileOut = new FileOutputStream(filePath + fileName);
//            wb.write(fileOut);
//            fileOut.close();
//            return "zipDic/" + fileName;
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            return "";
//        }
//        // 第六步，将文件存到指定位置
////        try {
////            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期
////            //输出Excel文件
////            OutputStream output = response.getOutputStream();
////            response.reset();
////            response.setHeader("Content-disposition", "attachment; filename=" + df.format(new Date()) + ".xls");
////            response.setContentType("application/msexcel");
//////            response.setContentType("application/vnd.ms-excel;charset=utf-8");// // 指定文件的保存类型。
////            response.setCharacterEncoding(fileEncoding);
////            wb.write(output);
////            output.close();
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
//    }
//
//
//    /**
//     * 描述：获取IO流中的数据，组装成List<List<Object>>对象
//     *
//     * @param in,fileName
//     * @return
//     * @throws IOException
//     */
//    public List<List<Object>> getBankListByExcel(InputStream in, String fileName) throws Exception {
//        List<List<Object>> list = null;
//
//        //创建Excel工作薄
//        Workbook work = this.getWorkbook(in, fileName);
//        if (null == work) {
//            throw new Exception("创建Excel工作薄为空！");
//        }
//        Sheet sheet = null;
//        Row row = null;
//        Cell cell = null;
//
//        list = new ArrayList<List<Object>>();
//        //遍历Excel中所有的sheet
//        for (int i = 0; i < work.getNumberOfSheets(); i++) {
//            sheet = work.getSheetAt(i);
//            if (sheet == null) {
//                continue;
//            }
//
//            //遍历当前sheet中的所有行
//            for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
//                row = sheet.getRow(j);
//                if (row == null || row.getFirstCellNum() == j) {
//                    continue;
//                }
//                //遍历所有的列
//                List<Object> li = new ArrayList<Object>();
//                for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
//                    cell = row.getCell(y);
//                    Object ce = this.getCellValue(cell);
//                    if (null != ce)
//                        li.add(this.getCellValue(cell));
//                    else
//                        li.add("");
//                }
//                list.add(li);
//            }
//        }
//        return list;
//    }
//
//
//    /**
//     * 描述：根据文件后缀，自适应上传文件的版本
//     *
//     * @param inStr,fileName
//     * @return
//     * @throws Exception
//     */
//    public Workbook getWorkbook(InputStream inStr, String fileName) throws Exception {
//        Workbook wb = null;
//        String fileType = fileName.substring(fileName.lastIndexOf("."));
//        if (excel2003L.equals(fileType)) {
//            wb = new HSSFWorkbook(inStr);  //2003-
//        } else if (excel2007U.equals(fileType)) {
//            wb = new XSSFWorkbook(inStr);  //2007+
//        } else {
//            throw new Exception("解析的文件格式有误！");
//        }
//        return wb;
//    }
//
//    /**
//     * 根据excel单元格类型获取excel单元格值
//     *
//     * @param cell
//     * @return
//     */
//    public String getCellValue(Cell cell) {
//        String cellvalue = "";
//        try {
//            if (cell != null) {
//                // 判断当前Cell的Type
//                switch (cell.getCellType()) {
//                    // 如果当前Cell的Type为NUMERIC
//                    case HSSFCell.CELL_TYPE_NUMERIC: {
//                        short format = cell.getCellStyle().getDataFormat();
//                        if (format == 14 || format == 31 || format == 57 || format == 58) {   //excel中的时间格式
//                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//                            double value = cell.getNumericCellValue();
//                            Date date = DateUtil.getJavaDate(value);
//                            cellvalue = sdf.format(date);
//                        }
//                        // 判断当前的cell是否为Date
//                        else if (HSSFDateUtil.isCellDateFormatted(cell)) {  //先注释日期类型的转换，在实际测试中发现HSSFDateUtil.isCellDateFormatted(cell)只识别2014/02/02这种格式。
//                            // 如果是Date类型则，取得该Cell的Date值           // 对2014-02-02格式识别不出是日期格式
//                            Date date = cell.getDateCellValue();
//                            DateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
//                            cellvalue = formater.format(date);
//                        } else { // 如果是纯数字
//                            // 取得当前Cell的数值
//                            cellvalue = NumberToTextConverter.toText(cell.getNumericCellValue());
//
//                        }
//                        break;
//                    }
//                    // 如果当前Cell的Type为STRIN
//                    case HSSFCell.CELL_TYPE_STRING:
//                        // 取得当前的Cell字符串
//                        cellvalue = cell.getStringCellValue().replaceAll("'", "''");
//                        break;
//                    case HSSFCell.CELL_TYPE_BLANK:
//                        cellvalue = null;
//                        break;
//                    // 默认的Cell值
//                    default: {
//                        cellvalue = " ";
//                    }
//                }
//            } else {
//                cellvalue = "";
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return cellvalue;
//    }
//}
