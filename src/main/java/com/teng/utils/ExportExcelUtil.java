package com.teng.utils;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.filechooser.FileSystemView;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.teng.entity.BookEntity;

/**
 * poi到处List集合数据到Excel
 * @author TENG
 *
 */
public class ExportExcelUtil {

	public static void ExportExcel(List<BookEntity> list) {  
        // 第一步，创建一个webbook，对应一个Excel文件  
        HSSFWorkbook wb = new HSSFWorkbook();  
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
        HSSFSheet sheet = wb.createSheet("网络爬虫-豆瓣书籍");  
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
        HSSFRow row = sheet.createRow((int) 0);  
        sheet.setColumnWidth(1, 9500);
        sheet.setColumnWidth(4, 9500);       
        sheet.setColumnWidth(5, 5500);
        sheet.setColumnWidth(6, 3000);
        sheet.setColumnWidth(7, 3000);
        // 第四步，创建单元格，并设置值表头 设置表头居中  
        HSSFCellStyle style = wb.createCellStyle();  
        style.setAlignment(HSSFCellStyle.ALIGN_LEFT); // 创建一个靠左格式  

        HSSFCell cell = row.createCell(0);
        cell.setCellValue("序号");  
        cell.setCellStyle(style);  
  
        cell = row.createCell(1); 
        cell.setCellValue("书名");  
        cell.setCellStyle(style);  
        
        cell = row.createCell(2);  
        cell.setCellValue("评分");  
        cell.setCellStyle(style);  
  
        cell = row.createCell(3);  
        cell.setCellValue("评价人数");  
        cell.setCellStyle(style);  
  
        cell = row.createCell(4);  
        cell.setCellValue("作者");  
        cell.setCellStyle(style);  
  
        cell = row.createCell(5);  
        cell.setCellValue("出版社");  
        cell.setCellStyle(style);  
  
        cell = row.createCell(6);  
        cell.setCellValue("出版日期");  
        cell.setCellStyle(style);  
  
        cell = row.createCell(7);  
        cell.setCellValue("价格");  
        cell.setCellStyle(style);  
  
        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，  
  
        for (int i = 0; i < list.size(); i++) {  
            row = sheet.createRow((int) i + 1);  
            BookEntity book = (BookEntity) list  
                    .get(i);  
            // 第六步，创建单元格，并设置值  
            row.createCell(0).setCellValue(String.valueOf(i+1));  
            row.createCell(1).setCellValue(book.getSubject());  
            row.createCell(2).setCellValue(book.getRating());  
            row.createCell(3).setCellValue(book.getRaterNum());  
  
            row.createCell(4).setCellValue(book.getAuthor());  
            row.createCell(5).setCellValue(book.getPublisher());  
            row.createCell(6).setCellValue(book.getPublishDate());  
            row.createCell(7).setCellValue(book.getPrice());  
        }  
        // 第七步，将文件存到指定位置  
        try {  
            FileSystemView fsv = FileSystemView.getFileSystemView();  
  
            Date currentTime = new Date();  
            SimpleDateFormat formatter = new SimpleDateFormat(  
                    "yyyy-MM-dd");  
            String dateString = formatter.format(currentTime);  
  
            String deskPath = fsv.getHomeDirectory().toString() + "/网络爬虫-豆瓣书籍"  
                    + dateString + ".xls";  
            FileOutputStream fout = new FileOutputStream(deskPath);  
            wb.write(fout);  
            fout.close();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }
}
