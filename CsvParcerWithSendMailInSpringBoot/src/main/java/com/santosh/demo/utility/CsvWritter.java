package com.santosh.demo.utility;

import com.santosh.demo.model.Product;
import org.apache.poi.xssf.usermodel.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
Santosh Kushwah
 */
public class CsvWritter {
    public static String fileName = "temp.csv";
    public static File file  = new File(fileName);
    public static String csvConverter(List<Product> productList, String headers){
        try {
            FileWriter csvWriter = new FileWriter(fileName());
            List<String[]> data = toStringArray(productList,headers);
            for (String[] rowData : data) {
                csvWriter.append(String.join(",", rowData));
                csvWriter.append("\n");
            }
            csvWriter.flush();
            csvWriter.close();
            //Send Mail
            //Mail.sendEmailWithAttachments(mailTo,
              //      subject, message, fileName());
            //file.deleteOnExit();

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return fileName;
    }
    private static List<String[]> toStringArray(List<Product> emps, String headers) {
        List<String[]> records = new ArrayList<String[]>();
        records.add(new String[] {headers});
        Iterator<Product> it = emps.iterator();
        while (it.hasNext()) {
            Product emp = it.next();
            records.add(new String[] {String.valueOf(emp.getId()), emp.getName(), String.valueOf(emp.getAge())});
        }
        return records;
    }
    public static String fileName() throws IOException {
        if(!file.exists()){
            file.createNewFile();
        }
        return fileName;
    }
}
