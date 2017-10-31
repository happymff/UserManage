package data;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by mengfeifei on 2017/8/23.
 */
public class DataReader {
    public void readData() throws IOException{
        InputStream is = new FileInputStream("/Users/mengfeifei/Desktop/TestData.xlsx");
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
        System.out.println(xssfWorkbook.getNumberOfSheets());
        for (int i = 0; i < xssfWorkbook.getNumberOfSheets(); i++) {
            XSSFSheet sheetAt = xssfWorkbook.getSheetAt(i);
            System.out.println("sheet"+(i+1)+":"+sheetAt.getSheetName());
            XSSFRow title = sheetAt.getRow(0);
            XSSFCell cf;

            for (int rowNum = 1; rowNum <= sheetAt.getLastRowNum(); rowNum++) {
                System.out.println("第"+(i+1)+"次取每行对应的值");
                for (int j = 0; j < title.getLastCellNum(); j++) {
                    cf = title.getCell(j);
                    System.out.println("title的名称：" + cf);
                    if (cf.toString().equals("ID")) {
                        XSSFCell r = sheetAt.getRow(rowNum).getCell(j);
                        System.out.println("ID：" + r);
                    }else if (cf.toString().equals("userName")){
                        XSSFCell r = sheetAt.getRow(rowNum).getCell(j);
                        System.out.println("userName：" + r);
                    }else if (cf.toString().equals("pwd")){
                        XSSFCell r = sheetAt.getRow(rowNum).getCell(j);
                        System.out.println("pwd：" + r);
                    }

                }
            }
        }
    }
}
