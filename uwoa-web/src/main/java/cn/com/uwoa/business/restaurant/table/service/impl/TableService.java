package cn.com.uwoa.business.restaurant.table.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cn.com.uwoa.business.restaurant.table.service.ITableService;
import cn.com.uwoa.system.assembly.AssemblyHelper;
import cn.com.uwoa.system.assembly.dao.impl.AssemblyDao;
import cn.com.uwoa.system.frame.po.FieldPo;
import cn.com.uwoa.system.frame.po.TablePo;
import cn.com.uwoa.system.frame.service.impl.FrameService;

/**
 * 餐厅设置/餐桌管理 - 业务服务层实现类
 * @author liyue
 */
@Service
public class TableService extends FrameService implements ITableService {
	/**
	 * 查询
	 * @param condMap 查询条件对象
	 * @param condPo 解析后查询条件对象
	 * @return 查询结果集
	 */
	@Override
	public Map<String,Object> query(Map<String,Object> condMap){
		//调用框架查询方法
		Map<String,Object> returnMap = super.query(condMap, new TablePo("rest_table"){
			/**
			 * 用户自定义
			 * 重载用户自定义方法，在此方法中对	Po中数据进行手工加工
			 */
			@Override
			public void userDefined() {
				//修改桌号的操作符
				if(fields.get("table_name")!=null){
					fields.get("table_name").setOperator("LIKE");
				}
				fields.put("id", new FieldPo("id","<>","4b43074422324fa78c6656c8fc7cf833"));
				fields.put("orderBy", new FieldPo("orderBy", "=", "order_num"));
			}
		});
		//手工处理
		try {
			AssemblyHelper.assembly((List)returnMap.get("data"), "sys_dictionary_item", "is_drinks", "is_drinks_name", "value", "name", " AND dictionary_key='GLOBAL_TF' ");
			AssemblyHelper.assembly((List)returnMap.get("data"), "sys_dictionary_item", "is_drinks", "is_staple_name", "value", "name", " AND dictionary_key='GLOBAL_TF' ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//返回
		return returnMap;
	}

	/**
	 * 新增
	 * @param dataMap 数据对象
	 * @return 主键
	 */
	@Override
	public String add(Map<String, Object> dataMap) {
		//调用框架查询方法
		return super.add(dataMap,  new TablePo("rest_table"));
	}
	
	/**
	 * 批量新增
	 * @param dataMap 数据对象
	 * @return 影响记录行数
	 */
	@Override
	public int addBatch(Map<String, Object> dataMap) {
		TablePo dataPo = new TablePo("rest_table");
		String prefix = (String)dataMap.get("prefix");
		String suffix = (String)dataMap.get("suffix");
		int serial_from = Integer.parseInt((String)dataMap.get("serial_from"));
		int serial_to = Integer.parseInt((String)dataMap.get("serial_to"));
		
		for(int i=serial_from;i<=serial_to;i++){
			Map row = new HashMap();
			row.put("table_name", prefix+i+suffix);
			row.put("person_count", dataMap.get("person_count"));
			row.put("rest_id", dataMap.get("rest_id"));
			//自动解析新增参数
			dataPo.setFields(autoAnalyzeQuery(row));
			//用户自定义
			dataPo.userDefined();
			
			//新增并返回
			frameDao.insert(dataPo);
		}
		return 1;
	}
	
	/**
	 * 修改
	 * @param dataMap 数据对象
	 * @return 影响记录行数
	 */
	@Override
	public int edit(Map<String, Object> dataMap) {
		//调用框架查询方法
		return super.edit(dataMap,  new TablePo("rest_table"));
	}
	
	/**
	 * 删除
	 * @param dataMap 数据对象
	 * @return 影响记录行数
	 */
	@Override
	public int delete(Map<String, Object> dataMap) {
		//调用框架查询方法
		return super.delete(dataMap,  new TablePo("rest_table"));
	}
}
