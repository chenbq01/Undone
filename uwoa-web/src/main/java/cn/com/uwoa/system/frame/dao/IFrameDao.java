package cn.com.uwoa.system.frame.dao;

import java.util.List;
import java.util.Map;

import cn.com.uwoa.system.frame.po.FieldPo;
import cn.com.uwoa.system.frame.po.PagePo;
import cn.com.uwoa.system.frame.po.TablePo;

/**
 * 基础框架 - 数据库持久层接口
 * @author liyue
 */
public interface IFrameDao {
	/**
	 * 查询
	 * @param condPo 查询条件对象
	 * @param pagePo 翻页信息
	 * @return 查询结果集
	 */
	public List<Map<String,Object>> select(TablePo condPo,PagePo pagePo);
	
	/**
	 * 查询
	 * @param field 查询字段
	 * @param condPo 查询条件对象
	 * @param pagePo 翻页信息
	 * @return 查询结果集
	 */
	public List<Map<String,Object>> select(String field,TablePo condPo,PagePo pagePo);
	
	/**
	 * 查询
	 * @param sql 查询语句
	 * @param pagePo 翻页信息
	 * @return 查询结果集
	 */
	public List<Map<String,Object>> selectBySql(String sql,PagePo pagePo);

	/**
	 * 新增
	 * @param dataPo 数据对象
	 * @return 主键
	 */
	public String insert(TablePo dataPo);
	
	/**
	 * 修改
	 * @param dataPo 数据对象
	 * @return 影响记录行数
	 */
	public int update(TablePo dataPo);
	
	/**
	 * 删除
	 * @param dataPo 数据对象
	 * @return 影响记录行数
	 */
	public int delete(TablePo dataPo);
	
	/**
	 * 真实删除
	 * @param dataPo 数据对象
	 * @return 影响记录行数
	 */
	public int deleteReal(TablePo dataPo);
	
	/**
	 * 直接执行sql语句
	 * @param sql
	 * @return
	 */
	public int executeNonQuery(String sql);
	
	/**
	 * 得到单个查询值
	 * @param sql
	 * @return
	 */
	public String getSingleData(String sql);
	
	
}
