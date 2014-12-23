package cn.com.uwoa.system.au.user.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cn.com.uwoa.system.assembly.AssemblyHelper;
import cn.com.uwoa.system.assembly.dao.impl.AssemblyDao;
import cn.com.uwoa.system.au.user.service.IUserService;
import cn.com.uwoa.system.frame.po.FieldPo;
import cn.com.uwoa.system.frame.po.SubTablePo;
import cn.com.uwoa.system.frame.po.TablePo;
import cn.com.uwoa.system.frame.service.impl.FrameService;
import cn.com.uwoa.system.tools.UUID;

/**
 * 系统管理/用户管理 - 业务服务层实现类
 * @author liyue
 */
@Service
public class UserService extends FrameService implements IUserService {
	/**
	 * 查询
	 * @param condMap 查询条件对象
	 * @return 查询结果集
	 */
	@Override
	public Map<String,Object> query(Map<String,Object> condMap){
		//调用框架查询方法
		Map<String,Object> returnMap = super.query(condMap, new TablePo("au_users"){
			/**
			 * 用户自定义
			 * 重载用户自定义方法，在此方法中对	Po中数据进行手工加工
			 */
			@Override
			public void userDefined() {
				//修改名称的操作符
				if(fields.get("username")!=null){
					fields.get("username").setOperator("LIKE");
				}
			}
		});
		//手工处理
		try {
			AssemblyHelper.assembly((List)returnMap.get("data"), "sys_dictionary_item", "enable", "enable_name", "value", "name", " AND dictionary_key='GLOBAL_ENABLE' ");
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
		Map<String,Object> returnMap = super.query(condMap, new TablePo("au_users_roles"){
			/**
			 * 用户自定义
			 * 重载用户自定义方法，在此方法中对	Po中数据进行手工加工
			 */
			@Override
			public void userDefined() {
				//修改编号的操作符
				if(fields.get("uid")!=null){
					fields.get("uid").setOperator("IN");
				}
			}
		});

		//手工处理
		try {
			AssemblyHelper.assembly((List)returnMap.get("data"), "au_roles", "rid", "rid_name", "id", "name", null);
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
		return super.add(dataMap,  new TablePo("au_users"){
			/**
			 * 用户自定义
			 * 重载用户自定义方法，在此方法中对	Po中数据进行手工加工
			 */
			@Override
			public void userDefined() {
				//修改编号的操作符
				if(fields.get("password").getValue().equals(fields.get("password_old").getValue())){
					fields.remove("password");
				}
				else{
					fields.get("password").setValue(UUID.Md5(fields.get("password").getValue()));
				}
				fields.remove("password_old");
				fields.remove("password2");
			}
		} , new SubTablePo("au_users_roles", "uid"));
	}
	
	/**
	 * 新增
	 * @param dataMap 数据对象
	 * @return 影响记录行数
	 */
	@Override
	public int edit(Map<String, Object> dataMap) {
		//调用框架查询方法
		return super.edit(dataMap,  new TablePo("au_users"){
			/**
			 * 用户自定义
			 * 重载用户自定义方法，在此方法中对	Po中数据进行手工加工
			 */
			@Override
			public void userDefined() {
				//修改编号的操作符
				if(fields.get("password").getValue().equals(fields.get("password_old").getValue())){
					fields.remove("password");
				}
				else{
					fields.get("password").setValue(UUID.Md5(fields.get("password").getValue()));
				}
				fields.remove("password_old");
				fields.remove("password2");
			}
		} , new SubTablePo("au_users_roles", "uid"));
	}
	
	/**
	 * 删除
	 * @param dataMap 数据对象
	 * @return 影响记录行数
	 */
	@Override
	public int delete(Map<String, Object> dataMap) {
		//调用框架查询方法
		return super.delete(dataMap,  new TablePo("au_users"));
	}
}
