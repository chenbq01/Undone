package cn.com.uwoa.system.dictionary.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cn.com.uwoa.system.assembly.AssemblyHelper;
import cn.com.uwoa.system.assembly.dao.impl.AssemblyDao;
import cn.com.uwoa.system.dictionary.DictionaryHelper;
import cn.com.uwoa.system.dictionary.service.IDictionaryService;
import cn.com.uwoa.system.frame.po.FieldPo;
import cn.com.uwoa.system.frame.po.SubTablePo;
import cn.com.uwoa.system.frame.po.TablePo;
import cn.com.uwoa.system.frame.service.impl.FrameService;

/**
 * 系统管理/数据字典 - 业务服务层实现类
 * @author liyue
 */
@Service
public class DictionaryService extends FrameService implements IDictionaryService {
	/**
	 * 查询
	 * @param condMap 查询条件对象
	 * @return 查询结果集
	 */
	@Override
	public Map<String,Object> query(Map<String,Object> condMap){
		//调用框架查询方法
		Map<String,Object> returnMap = super.query(condMap, new TablePo("sys_dictionary"){
			/**
			 * 用户自定义
			 * 重载用户自定义方法，在此方法中对	Po中数据进行手工加工
			 */
			@Override
			public void userDefined() {
				//修改编号的操作符
				if(fields.get("key")!=null){
					fields.get("key").setOperator("LIKE");
				}
				//修改名称的操作符
				if(fields.get("name")!=null){
					fields.get("name").setOperator("LIKE");
				}
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
	 * 查询子表
	 * @param condMap 查询条件对象
	 * @return 查询结果集
	 */
	@Override
	public Map<String,Object> querySub(Map<String,Object> condMap){
		//调用框架查询方法
		Map<String,Object> returnMap = super.query(condMap, new TablePo("sys_dictionary_item"){
			/**
			 * 用户自定义
			 * 重载用户自定义方法，在此方法中对	Po中数据进行手工加工
			 */
			@Override
			public void userDefined() {
				//修改编号的操作符
				if(fields.get("dictionary_id")!=null){
					fields.get("dictionary_id").setOperator("IN");
				}
			}
		});

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
		DictionaryHelper.dictionaryMap.clear();
		//调用框架查询方法
		return super.add(dataMap,  new TablePo("sys_dictionary") , new SubTablePo("sys_dictionary_item", "dictionary_id"){
			/**
			 * 用户自定义
			 * 重载用户自定义方法，在此方法中对	Po中数据进行手工加工
			 */
			@Override
			public void userDefined(Map data) {
				fields.put("dictionary_key", new FieldPo("dictionary_key", "=", (String)(data.get("key"))));
			}
		});
	}
	
	/**
	 * 新增
	 * @param dataMap 数据对象
	 * @return 影响记录行数
	 */
	@Override
	public int edit(Map<String, Object> dataMap) {
		DictionaryHelper.dictionaryMap.clear();
		//调用框架查询方法
		return super.edit(dataMap,  new TablePo("sys_dictionary") , new SubTablePo("sys_dictionary_item", "dictionary_id"){
			/**
			 * 用户自定义
			 * 重载用户自定义方法，在此方法中对	Po中数据进行手工加工
			 */
			@Override
			public void userDefined(Map data) {
				fields.put("dictionary_key", new FieldPo("dictionary_key", "=", (String)(data.get("key"))));
			}
		});
	}
	
	/**
	 * 删除
	 * @param dataMap 数据对象
	 * @return 影响记录行数
	 */
	@Override
	public int delete(Map<String, Object> dataMap) {
		DictionaryHelper.dictionaryMap.clear();
		//调用框架查询方法
		return super.delete(dataMap,  new TablePo("sys_dictionary"));
	}
}
