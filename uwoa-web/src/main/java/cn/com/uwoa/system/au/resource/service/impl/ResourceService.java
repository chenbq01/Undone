package cn.com.uwoa.system.au.resource.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cn.com.uwoa.system.assembly.AssemblyHelper;
import cn.com.uwoa.system.assembly.dao.impl.AssemblyDao;
import cn.com.uwoa.system.au.resource.service.IResourceService;
import cn.com.uwoa.system.frame.po.FieldPo;
import cn.com.uwoa.system.frame.po.TablePo;
import cn.com.uwoa.system.frame.service.impl.FrameService;
import cn.com.uwoa.system.tools.Pinyin;

/**
 * 系统管理/资源管理 - 业务服务层实现类
 * @author liyue
 */
@Service
public class ResourceService extends FrameService implements IResourceService {
	/**
	 * 查询
	 * @param condMap 查询条件对象
	 * @param condPo 解析后查询条件对象
	 * @return 查询结果集
	 */
	@Override
	public Map<String,Object> query(Map<String,Object> condMap){
		//调用框架查询方法
		Map<String,Object> returnMap = super.query(condMap, new TablePo("au_resources"){
			/**
			 * 用户自定义
			 * 重载用户自定义方法，在此方法中对	Po中数据进行手工加工
			 */
			@Override
			public void userDefined() {
				//修改名称的操作符
				if(fields.get("name")!=null){
					fields.get("name").setOperator("LIKE");
				}
			}
		});
		//手工处理
		try {
			AssemblyHelper.assembly((List)returnMap.get("data"), "sys_dictionary_item", "type", "type_name", "value", "name", " AND dictionary_key='RESOURCE_TYPE' ");
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
		return super.add(dataMap,  new TablePo("au_resources"){
			/**
			 * 用户自定义
			 * 重载用户自定义方法，在此方法中对	Po中数据进行手工加工
			 */
			@Override
			public void userDefined() {
//				FieldPo type_lcode=new FieldPo("type_lcode","=",Pinyin.getPinYin(fields.get("type_name").getValue()));
//				fields.put("type_lcode", type_lcode);
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
		//调用框架查询方法
		return super.edit(dataMap,  new TablePo("au_resources"));
	}
	
	/**
	 * 删除
	 * @param dataMap 数据对象
	 * @return 影响记录行数
	 */
	@Override
	public int delete(Map<String, Object> dataMap) {
		//调用框架查询方法
		return super.delete(dataMap,  new TablePo("au_resources"));
	}
}
