package com.team.car.handler;

import com.team.car.util.MessageSource;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * Created by ysw on 14-12-14.
 */
public class ReadFile {

    public static Iterator<Row> readFile(String fileName){
        boolean isE2007 = false;
        if(fileName.toLowerCase().endsWith(".xlsx"))
        {
            isE2007 = true;
        }
        try {
            InputStream input = new FileInputStream(fileName);
            Workbook wb  = null;
            if(isE2007){
                wb = new XSSFWorkbook(input);
            }
            else{
                wb = new HSSFWorkbook(input);
            }
            Sheet sheet = wb.getSheetAt(0);
            Iterator<Row> rows = sheet.rowIterator();
            return rows;
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }


    public static Map<Integer,Integer> getIdColumn(Iterator<Row> rows){
        String id = null;
        try {
            id = MessageSource.message("sheetColumn.id");
            String[] columns = MessageSource.message("sheetColumn").split("#");
            Row titleRow = rows.next();
            Iterator<Cell> cellIterator = titleRow.cellIterator();
            //输出的列位置，读取的列位置
            Map<Integer,Integer> map = new TreeMap<Integer,Integer>();
            int i = 0;
            while (cellIterator.hasNext()){
                Cell cell = cellIterator.next();
                if(id.equals(cell.getStringCellValue())){
                    map.put(0,i);
                }
                for(int index=0;index<columns.length;index ++){
                    if(columns[index].equals(cell.getStringCellValue().trim())){
                        map.put(index +1,i);
                    }
                }
                i++;
            }
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }




    public static void main(String[] args) {
        String filename = "E:\\BaiduYunDownload\\导出需求\\关爱明细查询.xls";
        Iterator<Row> rowIterator= readFile(filename);
        Map<Integer,Integer> map = getIdColumn(rowIterator);
        System.out.println(map);
    }
}
