package com.santosh;
import com.santosh.model.Product;
import com.santosh.utility.CsvWritter;
import com.santosh.utility.ExcelWriter;

import java.util.ArrayList;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(1,"Santosh Kushwah",30));
        String headers ="ID, Name, Age";
        String tomail = "santoshkumar021990@gmail.com"; String subject = "Test";
        String messageBody = "Test";
        CsvWritter.csvConverter(productList,headers,tomail,subject,messageBody);
        ExcelWriter.excelWritter(productList,tomail,subject,messageBody);
    }
}
