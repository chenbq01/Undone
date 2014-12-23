package cn.com.uwoa.system.frame.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.print.attribute.standard.Fidelity;

import org.springframework.beans.factory.annotation.Autowired;

import cn.com.uwoa.system.frame.dao.IFrameDao;
import cn.com.uwoa.system.frame.po.FieldPo;
import cn.com.uwoa.system.frame.po.PagePo;
import cn.com.uwoa.system.frame.po.SubTablePo;
import cn.com.uwoa.system.frame.po.TablePo;
import cn.com.uwoa.system.frame.service.IFrameService;

/**
 * 基础框架 - 业务服务层实现类
 * @author liyue
 */
public abstract class FrameService implements IFrameService {
	/**
	 * 数据库访问层
	 */
	@Autowired
	protected IFrameDao frameDao;

	/**
	 * 自动解析查询参数
	 * @param condMap 查询条件对象
	 * @return 解析后查询条件对象
	 */
	protected Map<String,FieldPo> autoAnalyzeQuery(Map<String,Object> condMap){
		// 声明返回对象
		Map<String,FieldPo> returnMap = new HashMap<String,FieldPo>();
		//遍历查询参数
		Iterator<String> it = condMap.keySet().iterator();
		while(it.hasNext()){
			//声明字段Po
			FieldPo fieldPo = new FieldPo();
			//记录字段名
			fieldPo.setName(it.next());
			//跳过特殊字段
			if(fieldPo.getName().equals("page") || fieldPo.getName().equals("orderby")){
				continue;
			}
			//默认运算符是=，如果需要其他运算符在各节点的Service类new TablePo的时候通过内部类重载userDefined()方法，在其中修改
			fieldPo.setOperator("=");
			//记录字段值
			fieldPo.setValue(String.valueOf(condMap.get(fieldPo.getName())));
			
			//记录整个字段
			returnMap.put(fieldPo.getName(), fieldPo);
		}
		
		//返回对象
		return returnMap;
	}
	
	/**
	 * 查询
	 * @param condMap 查询条件对象
	 * @param condPo 解析后查询条件对象
	 * @return 查询结果集
	 */
	protected Map<String,Object> query(Map<String,Object> condMap,TablePo condPo){
		//返回结果集
		Map<String,Object> returnMap = new HashMap<String,Object>(2);
		//获得页号
		int page=condMap.get("page")!=null?Integer.parseInt(condMap.get("page").toString()):1;
		
		//自动解析查询参数
		condPo.setFields(autoAnalyzeQuery(condMap));
		//用户自定义
		condPo.userDefined();
		
		//计算页信息
		PagePo pageInfo=null;
		if(page==-1){
			returnMap.put("pageInfo",pageInfo);
			returnMap.put("pageHtml","");
		}
		else{
			pageInfo=new PagePo(page,Integer.parseInt(frameDao.select("count(1) AS rowcount", condPo,null).get(0).get("rowcount").toString()));
			returnMap.put("pageInfo",pageInfo);
			returnMap.put("pageHtml",pageInfo.toHtml());
		}
		
		//查询
		returnMap.put("data",frameDao.select(condPo,pageInfo));
		
		//返回结果集
		return returnMap;
	}
	
	/**
	 * 查询
	 * @param condMap 查询条件对象
	 * @param sql SQL语句
	 * @return 查询结果集
	 */
	protected Map<String,Object> query(Map<String,Object> condMap,String sql){
		//返回结果集
		Map<String,Object> returnMap = new HashMap<String,Object>(2);
		//获得页号
		int page=condMap.get("page")!=null?Integer.parseInt(condMap.get("page").toString()):1;
		
		//计算页信息
		PagePo pageInfo=null;
		if(page==-1){
			returnMap.put("pageInfo",pageInfo);
			returnMap.put("pageHtml","");
		}
		else{
			pageInfo=new PagePo(page,Integer.parseInt(frameDao.selectBySql("SELECT COUNT(1) AS rowcount FROM ("+sql+") a", null).get(0).get("rowcount").toString()));
			returnMap.put("pageInfo",pageInfo);
			returnMap.put("pageHtml",pageInfo.toHtml());
		}
		
		//查询
		returnMap.put("data",frameDao.selectBySql(sql,pageInfo));
		
		//返回结果集
		return returnMap;
	}
	
	/**
	 * 查询子表
	 * @param condMap 查询条件对象
	 * @return 查询结果集
	 */
	@Override
	public Map<String,Object> querySub(Map<String,Object> condMap){
		return null;
	}
	
	/**
	 * 新增
	 * @param dataMap 数据对象
	 * @param dataPo 解析后查询条件对象
	 * @return 主键
	 */
	public String add(Map<String,Object> dataMap,TablePo dataPo){
		//自动解析新增参数
		dataPo.setFields(autoAnalyzeQuery(dataMap));
		//用户自定义
		dataPo.userDefined();
		
		//新增并返回
		return frameDao.insert(dataPo);
	}
	
	/**
	 * 新增
	 * @param dataMap 数据对象
	 * @param dataPo 解析后查询条件对象
	 * @param dataPo 子表解析后查询条件对象
	 * @return 主键
	 */
	public String add(Map<String,Object> dataMap,TablePo dataPo,SubTablePo subDataPo){
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
		
		//插入子表
		Iterator it = sub.values().iterator();
		while(it.hasNext()){
			Map subDataMap = (Map)it.next();
			subDataMap.put(subDataPo.getParentId(), id);
			//自动解析新增参数
			subDataPo.setFields(autoAnalyzeQuery(subDataMap));
			//用户自定义
			subDataPo.userDefined(dataMap);
			frameDao.insert(subDataPo);
		}
		return "";
	}
	
	/**
	 * 修改
	 * @param dataMap 数据对象
	 * @param dataPo 解析后查询条件对象
	 * @return 影响记录行数
	 */
	public int edit(Map<String,Object> dataMap,TablePo dataPo){
		//自动解析修改参数
		dataPo.setFields(autoAnalyzeQuery(dataMap));
		//用户自定义
		dataPo.userDefined();
		
		//新增并返回
		return frameDao.update(dataPo);
	}
	
	/**
	 * 修改
	 * @param dataMap 数据对象
	 * @param dataPo 解析后查询条件对象
	 * @return 影响记录行数
	 */
	public int edit(Map<String,Object> dataMap,TablePo dataPo,SubTablePo subDataPo){
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
			while(it.hasNext()){
				Map subDataMap = (Map)it.next();
				subDataMap.put(subDataPo.getParentId(), dataMap.get("id"));
				//自动解析新增参数
				subDataPo.setFields(autoAnalyzeQuery(subDataMap));
				//用户自定义
				subDataPo.userDefined(dataMap);
				if(subDataMap.get("id")==null || subDataMap.get("id").equals("")){
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
		else if(subDataPo.getDelete_model().equals("DELETE")){
			//删除
			SubTablePo deleteSubTablePo = new SubTablePo(subDataPo.getName(), subDataPo.getParentId());
			Map fieldsMap = new HashMap();
			fieldsMap.put(subDataPo.getParentId(), new FieldPo(subDataPo.getParentId()," = ",(String)dataMap.get("id")));
			deleteSubTablePo.setFields(fieldsMap);
			frameDao.deleteReal(deleteSubTablePo);
			
			String iuIds = "";
			while(it.hasNext()){
				Map subDataMap = (Map)it.next();
				subDataMap.put(subDataPo.getParentId(), dataMap.get("id"));
				//自动解析新增参数
				subDataPo.setFields(autoAnalyzeQuery(subDataMap));
				//用户自定义
				subDataPo.userDefined(dataMap);
				iuIds+="','"+frameDao.insert(subDataPo);
			}
		}
		return 1;
	}
	
	/**
	 * 删除
	 * @param dataMap 数据对象
	 * @param dataPo 解析后查询条件对象
	 * @return 影响记录行数
	 */
	public int delete(Map<String,Object> dataMap,TablePo dataPo){
		//自动解析修改参数
		dataPo.setFields(autoAnalyzeQuery(dataMap));
		//用户自定义
		dataPo.userDefined();
		
		//新增并返回
		return frameDao.delete(dataPo);
	}
	
	/**
	 * 得到查询的单个数据值
	 * @param sql
	 * @return
	 */
	public String getSingleInfo(String sql){
		
		return frameDao.getSingleData(sql);
		
	}
	
	/**
	 * 执行单个语句
	 * @param sql
	 * @return
	 */
	public int executeNonQuery(String sql){
		return frameDao.executeNonQuery(sql);
	}
	
	/**
	 * 查询
	 * @param sql 查询语句
	 * @param pagePo 翻页信息
	 * @return 查询结果集
	 */
	@Override
	public List<Map<String,Object>> queryBySql(String sql,PagePo pagePo){
		return frameDao.selectBySql(sql, pagePo);
	}
}
