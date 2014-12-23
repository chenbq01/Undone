package cn.com.uwoa.business.food.food.web;

import org.apache.poi.hssf.usermodel.HSSFCell;

public class ExcelHelper {
  /**  
  * 得到Excel表中的值  
  *  
  * @param hssfCell  
  *            Excel中的每一个格子  
  * @return Excel中每一个格子中的值  
  */ 
 public static String getValue(HSSFCell hssfCell) {  
     if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {  
         // 返回布尔类型的值  
         return String.valueOf(hssfCell.getBooleanCellValue());  
     } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {  
         // 返回数值类型的值  
         return String.valueOf(hssfCell.getNumericCellValue());  
     } else {  
         // 返回字符串类型的值  
         return String.valueOf(hssfCell.getStringCellValue());  
     }  
 } 
}
