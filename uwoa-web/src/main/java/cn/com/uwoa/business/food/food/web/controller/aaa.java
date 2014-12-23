package cn.com.uwoa.business.food.food.web.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import cn.com.uwoa.system.assembly.AssemblyHelper;

public class aaa {

	public static void main(String[] args) throws IOException {
		InputStream is = new FileInputStream("e:\\aaa.xls");  
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);  
        Map xlsDto = null;  
        List<Map> list = new ArrayList<Map>();  
        // 循环工作表Sheet  
        HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);  
        // 循环行Row  
        for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {  
            HSSFRow hssfRow = hssfSheet.getRow(rowNum);  
            if (hssfRow == null) {  
                continue;  
            }  
            xlsDto = new HashMap();  
            xlsDto.put("food_code",String.valueOf(hssfRow.getCell(0).getStringCellValue()));  
            xlsDto.put("food_name",String.valueOf(hssfRow.getCell(1).getStringCellValue()));
            xlsDto.put("type_name",String.valueOf(hssfRow.getCell(2).getStringCellValue()));
            xlsDto.put("mnem_code",String.valueOf(hssfRow.getCell(3).getStringCellValue()));
            xlsDto.put("unit_name",String.valueOf(hssfRow.getCell(4).getStringCellValue()));
            xlsDto.put("price",String.valueOf(hssfRow.getCell(5).getNumericCellValue()));
            xlsDto.put("is_new_name",String.valueOf(hssfRow.getCell(6).getStringCellValue()));
            xlsDto.put("is_special_name",String.valueOf(hssfRow.getCell(7).getStringCellValue()));
            xlsDto.put("is_weigh_name",String.valueOf(hssfRow.getCell(8).getStringCellValue()));
            xlsDto.put("order_num",String.valueOf(hssfRow.getCell(9).getStringCellValue()));
            xlsDto.put("food_intro",String.valueOf(hssfRow.getCell(10).getStringCellValue()));
            xlsDto.put("memo",String.valueOf(hssfRow.getCell(11).getStringCellValue()));
            list.add(xlsDto);  
        }
        
        try {
			AssemblyHelper.assembly(list, "food_foodtype", "type_name", "type_id", "type_name", "id", null);
			AssemblyHelper.assembly(list, "sys_dictionary_item", "unit_name", "unit", "name", "value", " AND dictionary_key='FOOD_UNIT' ");
			AssemblyHelper.assembly(list, "sys_dictionary_item", "is_new_name", "is_new", "name", "value", " AND dictionary_key='GLOBAL_TF' ");
			AssemblyHelper.assembly(list, "sys_dictionary_item", "is_special_name", "is_special", "name", "value", " AND dictionary_key='GLOBAL_TF' ");
			AssemblyHelper.assembly(list, "sys_dictionary_item", "is_weigh_name", "is_weigh", "name", "value", " AND dictionary_key='GLOBAL_TF' ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}

}
