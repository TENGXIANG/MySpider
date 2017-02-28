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
 * poi����List�������ݵ�Excel
 * @author TENG
 *
 */
public class ExportExcelUtil {

	public static void ExportExcel(List<BookEntity> list) {  
        // ��һ��������һ��webbook����Ӧһ��Excel�ļ�  
        HSSFWorkbook wb = new HSSFWorkbook();  
        // �ڶ�������webbook�����һ��sheet,��ӦExcel�ļ��е�sheet  
        HSSFSheet sheet = wb.createSheet("��������-�����鼮");  
        // ����������sheet����ӱ�ͷ��0��,ע���ϰ汾poi��Excel����������������short  
        HSSFRow row = sheet.createRow((int) 0);  
        sheet.setColumnWidth(1, 9500);
        sheet.setColumnWidth(4, 9500);       
        sheet.setColumnWidth(5, 5500);
        sheet.setColumnWidth(6, 3000);
        sheet.setColumnWidth(7, 3000);
        // ���Ĳ���������Ԫ�񣬲�����ֵ��ͷ ���ñ�ͷ����  
        HSSFCellStyle style = wb.createCellStyle();  
        style.setAlignment(HSSFCellStyle.ALIGN_LEFT); // ����һ�������ʽ  

        HSSFCell cell = row.createCell(0);
        cell.setCellValue("���");  
        cell.setCellStyle(style);  
  
        cell = row.createCell(1); 
        cell.setCellValue("����");  
        cell.setCellStyle(style);  
        
        cell = row.createCell(2);  
        cell.setCellValue("����");  
        cell.setCellStyle(style);  
  
        cell = row.createCell(3);  
        cell.setCellValue("��������");  
        cell.setCellStyle(style);  
  
        cell = row.createCell(4);  
        cell.setCellValue("����");  
        cell.setCellStyle(style);  
  
        cell = row.createCell(5);  
        cell.setCellValue("������");  
        cell.setCellStyle(style);  
  
        cell = row.createCell(6);  
        cell.setCellValue("��������");  
        cell.setCellStyle(style);  
  
        cell = row.createCell(7);  
        cell.setCellValue("�۸�");  
        cell.setCellStyle(style);  
  
        // ���岽��д��ʵ������ ʵ��Ӧ������Щ���ݴ����ݿ�õ���  
  
        for (int i = 0; i < list.size(); i++) {  
            row = sheet.createRow((int) i + 1);  
            BookEntity book = (BookEntity) list  
                    .get(i);  
            // ��������������Ԫ�񣬲�����ֵ  
            row.createCell(0).setCellValue(String.valueOf(i+1));  
            row.createCell(1).setCellValue(book.getSubject());  
            row.createCell(2).setCellValue(book.getRating());  
            row.createCell(3).setCellValue(book.getRaterNum());  
  
            row.createCell(4).setCellValue(book.getAuthor());  
            row.createCell(5).setCellValue(book.getPublisher());  
            row.createCell(6).setCellValue(book.getPublishDate());  
            row.createCell(7).setCellValue(book.getPrice());  
        }  
        // ���߲������ļ��浽ָ��λ��  
        try {  
            FileSystemView fsv = FileSystemView.getFileSystemView();  
  
            Date currentTime = new Date();  
            SimpleDateFormat formatter = new SimpleDateFormat(  
                    "yyyy-MM-dd");  
            String dateString = formatter.format(currentTime);  
  
            String deskPath = fsv.getHomeDirectory().toString() + "/��������-�����鼮"  
                    + dateString + ".xls";  
            FileOutputStream fout = new FileOutputStream(deskPath);  
            wb.write(fout);  
            fout.close();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }
}
