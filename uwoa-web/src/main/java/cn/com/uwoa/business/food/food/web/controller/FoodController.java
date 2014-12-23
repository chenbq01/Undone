package cn.com.uwoa.business.food.food.web.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.uwoa.business.food.food.service.IFoodService;
import cn.com.uwoa.business.food.food.web.ExcelHelper;
import cn.com.uwoa.global.security.SecurityHelper;
import cn.com.uwoa.system.assembly.AssemblyHelper;
import cn.com.uwoa.system.frame.service.IFrameService;
import cn.com.uwoa.system.frame.web.controller.FrameController;
import cn.com.uwoa.system.tools.UUID;

/**
 * 菜品管理/菜品维护管理 - 控制器
 * @author liyue
 */
@Controller
public class FoodController extends FrameController {
	/**
	 * 日志对象
	 */
	private static final Logger logger = LoggerFactory.getLogger(FoodController.class);

	/**
	 * 服务对象
	 */
	@Autowired
	private IFoodService foodService;
	
	/**
	 * 获得服务对象
	 * @return获得服务对象
	 */
	@Override
	protected IFrameService getService() {
		return (IFrameService)foodService;
	}

	/**
	 * 获取页面
	 * @return 框架页路径
	 */
	@RequestMapping(value = "/food/food", method = RequestMethod.GET)
	@Override
	public String getPage() {
		return "/food/food/foodList";
	}
	
	/**
	 * 查询
	 * @param condMap 查询条件对象
	 * @return 查询结果集
	 */
	@RequestMapping(value = "/food/food/query", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> query(@RequestBody Map<String,Object> condMap) {
		//调用框架查询方法
		condMap.put("rest_id", SecurityHelper.getRestId());
		return super.query(condMap);
	}
	
	/**
	 * 新增/修改
	 * @param dataMap 数据对象
	 * @return 执行结果
	 */
	@RequestMapping(value = "/food/food/edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> edit(@RequestBody Map<String,Object> dataMap) {
		//调用框架查询方法
		return super.edit(dataMap);
	}
	
	/**
	 * 删除
	 * @param dataMap 数据对象
	 * @return 执行结果
	 */
	@RequestMapping(value = "/food/food/delete", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delete(@RequestBody Map<String,Object> dataMap) {
		//调用框架查询方法
		dataMap.put("rest_id", SecurityHelper.getRestId());
		return super.delete(dataMap);
	}
	
	private String uploadpath;
	
	@Value("${demo.attachment.path}")
	public void setUploadpath(String uploadpath) {
		this.uploadpath = uploadpath;
	}
	
	/**
	 * 导入
	 * @param dataMap 数据对象
	 * @return 执行结果
	 * @throws IOException 
	 */
	@RequestMapping(value = "/food/food/importExcel", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> importExcel(@RequestBody Map<String,Object> dataMap) throws IOException {
		InputStream is = new FileInputStream(uploadpath+"/"+SecurityHelper.getRestId()+"/"+dataMap.get("fileName"));  
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);  
        Map<String,String> xlsDto = null;  
        List<Map<String,String>> list = new ArrayList<Map<String,String>>();  
        // 循环工作表Sheet  
        HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);  
        // 循环行Row  
        for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {  
            HSSFRow hssfRow = hssfSheet.getRow(rowNum);  
            if (hssfRow == null) {  
                continue;  
            }  
            xlsDto = new HashMap<String,String>();  
            xlsDto.put("food_code",hssfRow.getCell(0)==null?"":hssfRow.getCell(0).toString());  
            xlsDto.put("food_name",hssfRow.getCell(1)==null?"":hssfRow.getCell(1).toString());
            xlsDto.put("type_name",hssfRow.getCell(2)==null?"":hssfRow.getCell(2).toString());
            xlsDto.put("mnem_code",hssfRow.getCell(3)==null?"":hssfRow.getCell(3).toString());
            xlsDto.put("unit_name",hssfRow.getCell(4)==null?"":hssfRow.getCell(4).toString());
            xlsDto.put("price",hssfRow.getCell(5)==null?"":hssfRow.getCell(5).toString());
            xlsDto.put("is_new_name",hssfRow.getCell(6)==null?"":hssfRow.getCell(6).toString());
            xlsDto.put("is_special_name",hssfRow.getCell(7)==null?"":hssfRow.getCell(7).toString());
            xlsDto.put("is_weigh_name",hssfRow.getCell(8)==null?"":hssfRow.getCell(8).toString());
            xlsDto.put("order_num",hssfRow.getCell(9)==null?"":hssfRow.getCell(9).toString());
            xlsDto.put("food_intro",hssfRow.getCell(10)==null?"":hssfRow.getCell(10).toString());
            xlsDto.put("memo",hssfRow.getCell(11)==null?"":hssfRow.getCell(11).toString());
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
        
        // 声明返回对象
      	Map<String, Object> returnMap = new HashMap<String, Object>(1);
        
      	for(int i=0;i<list.size();i++){
			Map row = list.get(i);
			if(row.get("type_id")==null || row.get("type_id").equals("")){
				returnMap.put("success", "error");
				returnMap.put("errorinfo",  row.get("type_name")+"不存在!");
				return returnMap;
			}
		}
        ((IFoodService)getService()).importExcel(list);
        
    	
 		// 标记成功
 		returnMap.put("success", "true");
     		
        return returnMap;
	}
}
