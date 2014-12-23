package cn.com.uwoa.system.demo.student.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cn.com.uwoa.system.demo.student.service.IStudentService;
import cn.com.uwoa.system.frame.po.FieldPo;
import cn.com.uwoa.system.frame.po.TablePo;
import cn.com.uwoa.system.frame.service.impl.FrameService;

/**
 * 样例学生管理 - 业务服务层实现类
 * @author liyue
 */
@Service
public class StudentService extends FrameService implements IStudentService {
	/**
	 * 查询
	 * @param condMap 查询条件对象
	 * @param condPo 解析后查询条件对象
	 * @return 查询结果集
	 */
	@Override
	public Map<String,Object> query(Map<String,Object> condMap){
		//调用框架查询方法
		return super.query(condMap, new TablePo("sys_demo_student"){
			/**
			 * 用户自定义
			 * 重载用户自定义方法，在此方法中对	Po中数据进行手工加工
			 */
			@Override
			public void userDefined() {
				//修改姓名的操作符
				if(fields.get("name")!=null){
					fields.get("name").setOperator("LIKE");
				}
			}
		});
	}

	/**
	 * 新增
	 * @param dataMap 数据对象
	 * @return 主键
	 */
	@Override
	public String add(Map<String, Object> dataMap) {
		//调用框架查询方法
		return super.add(dataMap,  new TablePo("sys_demo_student"));
	}
	
	/**
	 * 新增
	 * @param dataMap 数据对象
	 * @return 影响记录行数
	 */
	@Override
	public int edit(Map<String, Object> dataMap) {
		//调用框架查询方法
		return super.edit(dataMap,  new TablePo("sys_demo_student"));
	}
	
	/**
	 * 删除
	 * @param dataMap 数据对象
	 * @return 影响记录行数
	 */
	@Override
	public int delete(Map<String, Object> dataMap) {
		//调用框架查询方法
		return super.delete(dataMap,  new TablePo("sys_demo_student"));
	}

	@Override
	public Map<String, Object> querySub(Map<String, Object> condMap) {
		// TODO Auto-generated method stub
		return null;
	}
}
