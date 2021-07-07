package com.santosh.utility;

import com.santosh.mail.Mail;
import com.santosh.model.Product;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.mail.MessagingException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelWriter {
    private static XSSFWorkbook workbook = new XSSFWorkbook();
    public static XSSFSheet sheet = workbook.createSheet("Product");
    public static void excelWritter(List<Product> productList, String mailTo, String subject, String message){
        String f = "temp.xlsx";
        File file = new File(f);
        try {
            int rowCount = 1;
            CellStyle style = workbook.createCellStyle();
            XSSFFont font = workbook.createFont();
            font.setFontHeight(14);
            style.setFont(font);
            writeHeaderLine();
            for (Product user : productList) {
                Row row = sheet.createRow(rowCount++);
                int columnCount = 0;
                createCell(row, columnCount++, user.getId(), style);
                createCell(row, columnCount++, user.getName(), style);
                createCell(row, columnCount++, user.getAge(), style);
            }
            FileOutputStream out = new FileOutputStream(file);
            workbook.write(out);
            out.close();
            Mail.sendEmailWithAttachments(mailTo,
                    subject, message, f);
        } catch (IOException | MessagingException e) {
            e.printStackTrace();
        }
        file.deleteOnExit();
    }
    private static void writeHeaderLine() {
        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
        createCell(row, 0, "ID", style);
        createCell(row, 1, "NAME", style);
        createCell(row, 2, "AGE", style);
    }
    private static void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }
}
