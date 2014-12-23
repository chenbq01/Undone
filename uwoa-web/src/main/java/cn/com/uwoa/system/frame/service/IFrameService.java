package cn.com.uwoa.system.frame.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;

import cn.com.uwoa.system.frame.po.FieldPo;
import cn.com.uwoa.system.frame.po.PagePo;
import cn.com.uwoa.system.frame.po.TablePo;

/**
 * 基础框架 - 业务服务层接口
 * @author liyue
 */
public interface IFrameService {
	/**
	 * 查询
	 * @param condMap 查询条件对象
	 * @return 查询结果集
	 */
	public Map<String,Object> query(Map<String,Object> condMap);
	
	/**
	 * 查询子表
	 * @param condMap 查询条件对象
	 * @param condPo 解析后查询条件对象
	 * @return 查询结果集
	 */
	public Map<String,Object> querySub(Map<String,Object> condMap);
	
	/**
	 * 新增
	 * @param dataMap 数据对象
	 * @return 主键
	 */
	public String add(Map<String,Object> dataMap);
	
	/**
	 * 修改
	 * @param dataMap 数据对象
	 * @return 影响记录行数
	 */
	public int edit(Map<String,Object> dataMap);
	
	/**
	 * 删除
	 * @param dataMap 数据对象
	 * @return 影响记录行数
	 */
	public int delete(Map<String,Object> dataMap);
	
	/**
	 * 得到查询的单个数据值
	 * @param sql
	 * @return
	 */
	public String getSingleInfo(String sql);
	
	/**
	 * 执行单个语句
	 * @param sql
	 * @return
	 */
	public int executeNonQuery(String sql);

	/**
	 * 查询
	 * @param sql 查询语句
	 * @param pagePo 翻页信息
	 * @return 查询结果集
	 */
	List<Map<String, Object>> queryBySql(String sql, PagePo pagePo);
	
}
