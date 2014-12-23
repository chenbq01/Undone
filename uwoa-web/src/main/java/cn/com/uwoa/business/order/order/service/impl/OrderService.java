package cn.com.uwoa.business.order.order.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.stereotype.Service;

import cn.com.uwoa.business.order.order.service.IOrderService;
import cn.com.uwoa.global.security.SecurityHelper;
import cn.com.uwoa.global.util.OrderNo;
import cn.com.uwoa.system.assembly.AssemblyHelper;
import cn.com.uwoa.system.assembly.dao.impl.AssemblyDao;
import cn.com.uwoa.system.frame.po.FieldPo;
import cn.com.uwoa.system.frame.po.SubTablePo;
import cn.com.uwoa.system.frame.po.TablePo;
import cn.com.uwoa.system.frame.service.impl.FrameService;

/**
 * 订单管理/订单列表 - 业务服务层实现类
 * @author liyue
 */
@Service
public class OrderService extends FrameService implements IOrderService {
	/**
	 * 查询
	 * @param condMap 查询条件对象
	 * @return 查询结果集
	 */
	@Override
	public Map<String,Object> query(Map<String,Object> condMap){
		//调用框架查询方法
		Map<String,Object> returnMap = super.query(condMap, new TablePo("ord_order"){
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
			AssemblyHelper.assembly((List)returnMap.get("data"), "rest_table", "table_id", "table_name", "id", "table_name", null);
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
		Map<String,Object> returnMap = super.query(condMap, new TablePo("ord_order_item"){
			/**
			 * 用户自定义
			 * 重载用户自定义方法，在此方法中对	Po中数据进行手工加工
			 */
			@Override
			public void userDefined() {
				//修改编号的操作符
				if(fields.get("order_id")!=null){
					fields.get("order_id").setOperator("IN");
				}
			}
		});

		List subList = (List)returnMap.get("data");
		Map subMap = new TreeMap();
		List newSubList = new ArrayList();
		//手工处理
		try {
			AssemblyHelper.assembly(subList, "food_food", "food_id", "food_name", "id", "food_name", null);
			AssemblyHelper.assembly(subList, "food_food", "food_id", "unit", "id", "unit", null);
			AssemblyHelper.assembly(subList, "sys_dictionary_item", "unit", "unit_name", "value", "name", " AND dictionary_key='FOOD_UNIT' ");
			AssemblyHelper.assembly(subList, "ord_batch", "batch_id", "batch_no", "id", "order_batch", null);
			AssemblyHelper.assembly(subList, "ord_batch", "batch_id", "memo", "id", "memo", null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		for(int i=0;i<subList.size();i++){
			Map subRow = (Map)subList.get(i);
			if(subMap.get(subRow.get("batch_no"))==null){
				subMap.put(subRow.get("batch_no"),new ArrayList());
			}
			((List)subMap.get(subRow.get("batch_no"))).add(subRow);
		}
		Iterator it = subMap.values().iterator();
		while(it.hasNext()){
			newSubList.add(it.next());
		}
		returnMap.put("data",newSubList);
		
		//返回
		return returnMap;
	}
	
	/**
	 * 新增
	 * @param dataMap 数据对象
	 * @return 影响记录行数
	 */
	@Override
	public String add(Map<String, Object> dataMap) {
		dataMap.put("order_no", OrderNo.generatebyRest_id(SecurityHelper.getRestId()));
		TablePo dataPo=new TablePo("ord_order");
		SubTablePo subDataPo =new SubTablePo("ord_order_item", "order_id");
		
		//保存子表
		Map sub = null;
		if(dataMap.get("sub")!=null){
			sub=(Map)dataMap.get("sub");
			dataMap.remove("sub");
		}
		
		//自动解析新增参数
		dataPo.setFields(autoAnalyzeQuery(dataMap));
		//用户自定义
		dataPo.userDefined();
		
		//新增并返回
		String id = frameDao.insert(dataPo);
		
		Map batchMap = new HashMap();
		batchMap.put("order_id", id);
		batchMap.put("order_batch", "0");
		TablePo batchPo=new TablePo("ord_batch");
		//自动解析新增参数
		batchPo.setFields(autoAnalyzeQuery(batchMap));
		//用户自定义
		batchPo.userDefined();
		//新增并返回
		String batchId = frameDao.insert(batchPo);
		
		//插入子表
		Iterator it = sub.values().iterator();
		while(it.hasNext()){
			Map subDataMap = (Map)it.next();
			subDataMap.put(subDataPo.getParentId(), id);
			subDataMap.put("batch_id", batchId);
			//自动解析新增参数
			subDataPo.setFields(autoAnalyzeQuery(subDataMap));
			//用户自定义
			subDataPo.userDefined(dataMap);
			frameDao.insert(subDataPo);
		}
		return "";
	}
	
	/**
	 * 新增
	 * @param dataMap 数据对象
	 * @return 影响记录行数
	 */
	@Override
	public int edit(Map<String, Object> dataMap) {
		TablePo dataPo=new TablePo("ord_order");
		SubTablePo subDataPo=new SubTablePo("ord_order_item", "order_id");
		
		//保存子表
		Map sub = null;
		if(dataMap.get("sub")!=null){
			sub=(Map)dataMap.get("sub");
			dataMap.remove("sub");
		}
		else{
			sub=new HashMap();
		}
		
		//自动解析修改参数
		dataPo.setFields(autoAnalyzeQuery(dataMap));
		//用户自定义
		dataPo.userDefined();
		
		//新增并返回
		frameDao.update(dataPo);
		
		//插入子表
		Iterator it = sub.values().iterator();
		if(subDataPo.getDelete_model().equals("UPDATE")){
			String iuIds = "";
			String batchId=null;
			while(it.hasNext()){
				Map subDataMap = (Map)it.next();
				subDataMap.put(subDataPo.getParentId(), dataMap.get("id"));
				//自动解析新增参数
				subDataPo.setFields(autoAnalyzeQuery(subDataMap));
				//用户自定义
				subDataPo.userDefined(dataMap);
				if(subDataMap.get("id")==null || subDataMap.get("id").equals("")){
					if(batchId==null){
						Map batchMap = new HashMap();
						batchMap.put("order_id", dataMap.get("id"));
						batchMap.put("order_batch", frameDao.getSingleData("SELECT count(1) from ord_batch WHERE order_id='"+dataMap.get("id")+"'"));
						TablePo batchPo=new TablePo("ord_batch");
						//自动解析新增参数
						batchPo.setFields(autoAnalyzeQuery(batchMap));
						//用户自定义
						batchPo.userDefined();
						//新增并返回
						batchId = frameDao.insert(batchPo);
					}
					
					subDataPo.getFields().put("batch_id",new FieldPo("batch_id", "=", batchId));
					iuIds+="','"+frameDao.insert(subDataPo);
				}
				else{
					iuIds+="','"+subDataMap.get("id");
					frameDao.update(subDataPo);
				}
			}
			//删除
			SubTablePo deleteSubTablePo = new SubTablePo(subDataPo.getName(), subDataPo.getParentId());
			Map fieldsMap = new HashMap();
			fieldsMap.put("id", new FieldPo("id"," NOT IN",iuIds));
			fieldsMap.put(subDataPo.getParentId(), new FieldPo(subDataPo.getParentId(),"=",(String)dataMap.get("id")));
			deleteSubTablePo.setFields(fieldsMap);
			frameDao.delete(deleteSubTablePo);
		}
		return 1;
	}
	
	/**
	 * 删除
	 * @param dataMap 数据对象
	 * @return 影响记录行数
	 */
	@Override
	public int delete(Map<String, Object> dataMap) {
		//调用框架查询方法
		return super.delete(dataMap,  new TablePo("ord_order"));
	}
	
	/**
	 * 结账
	 * @param dataMap 数据对象
	 * @return 影响记录行数
	 */
	@Override
	public int checkout(Map<String, Object> dataMap) {
		TablePo dataPo=new TablePo("ord_order");
		SubTablePo subDataPo=new SubTablePo("ord_order_item", "order_id");
		
		//保存子表
		Map sub = null;
		if(dataMap.get("sub")!=null){
			sub=(Map)dataMap.get("sub");
			dataMap.remove("sub");
		}
		else{
			sub=new HashMap();
		}
		
		//自动解析修改参数
		dataPo.setFields(autoAnalyzeQuery(dataMap));
		//用户自定义
		dataPo.userDefined();
		
		//新增并返回
		frameDao.update(dataPo);
		
		return 1;
	}
}
