package cn.com.uwoa.system.assembly.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.uwoa.system.assembly.dao.IAssemblyDao;
import cn.com.uwoa.system.assembly.service.IAssemblyService;

/**
 * 功能、用途、现存BUG:
 * 
 * @author 李鑫
 * @version 1.0.0
 * @see 需要参见的其它类
 * @since 1.0.0
 */
@Service
public class AssemblyService implements IAssemblyService{
	@Autowired
    private IAssemblyDao assemblyDao = null;
    
    
	/**
	 * @return 返回 assemblyDao。
	 */
	public IAssemblyDao getAssemblyDao() {
		return assemblyDao;
	}
	
	/**
	 * @param assemblyDao 要设置的 assemblyDao。
	 */
	public void setAssemblyDao(IAssemblyDao assemblyDao) {
		this.assemblyDao = assemblyDao;
	}
	
	/* （非 Javadoc）
	 * @see com.use.iso9000.assembly.bs.IAssemblyBs#findTargetBySource(java.lang.String)
	 */
	public HashMap findTargetBySource(String lData,String tableName,String sourceField,String targetField,String condition) {
		  HashMap map = new HashMap();
	     Iterator it = (getAssemblyDao().findTargetBySource(lData,tableName,sourceField,targetField,condition)).iterator();
	     while(it.hasNext()){
	         String strValue[]  = (String[]) it.next();
	         map.put(strValue[0],strValue[1]);
	     }
	     return map;
      }
	
	
	/* （非 Javadoc）
	 * @see com.use.iso9000.assembly.bs.IAssemblyBs#findTargetBySource(java.lang.String, java.lang.String, java.lang.String, java.lang.String[])
	 */
	public HashMap findTargetBySource(String lData, String tableName, String sourceField, String[] targetField,String condition) {
		// TODO 自动生成方法存根
		HashMap map = new HashMap();
		Iterator it = (getAssemblyDao().findTargetBySource(lData,tableName,sourceField,targetField,condition)).iterator();
		while(it.hasNext()){
         String strValue[]  = (String[]) it.next();
         List lst = new ArrayList(Arrays.asList(strValue));
         
         map.put(strValue[0],lst);
      }
		return map;
	}
}
