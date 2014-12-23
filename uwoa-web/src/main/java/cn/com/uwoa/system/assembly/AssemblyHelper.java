package cn.com.uwoa.system.assembly;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cn.com.uwoa.system.assembly.service.IAssemblyService;
import cn.com.uwoa.system.tools.SpringContextHolder;

/**
 * 功能、用途、现存BUG:
 * 进行字符串范围的vos/vo字段装配
 * 
 * @author 李鑫
 * @version 1.0.0
 * @see 需要参见的其它类
 * @since 1.0.0
 */
public class AssemblyHelper {
	
	/**
	 * Sql 语句中In中包含的字段个数
	 */
	public static final int SQL_IN_MAX_FIELDS = 900;
	
	/**
	 * 记录非class的基本数据类型
	 */
	public static final List LST_JAVA_TYPES = Arrays.asList(new String[]{"boolean", "byte", "char", "short", "int", "long", "float", "double", "void"}); 

	/**
	 * 把一个List拆分成几个List，每个List的成员数不超过size
	 */
	private static final List splitList(List l, int size) {
		List result = new ArrayList();
		if (l.size() <= size) {
			//  不需要拆分的情况
			result.add(l);
			return result;
		}

		int j=0;
		for (int i = 0; i <= l.size(); i += size) {
			//	按照size将原List分成若干子List
			int indexFrom = j * size;
			int indexTo = (j + 1) * size ;
			if (indexTo >= l.size()) {
				indexTo = l.size() ;
			}
			List subList = l.subList(indexFrom, indexTo);
			result.add(subList);
			j++;
		}

		return result;
	}
	
	/**
	 * 把一个List拆分成几个List，每个List的成员数不超过默认值
	 */
	private static final List splitList(List l) {
		return splitList(l, SQL_IN_MAX_FIELDS);
	}

	public static IAssemblyService getAssemblyBs() {
//		return (IAssemblyService) Helper.getBean("assembly_bs_target");  //得到BS对象
		return SpringContextHolder.getBean("assemblyService");
	}

	/**
	 * 对对象字段进行一对一字段的装配，对象支持List、Map、Vo，工具自动判断
	 * 
	 * @param obj 要装配的对象，集合用List（里面装Map或Vo），Map或Vo都会被认为是1个对象
	 * @param tableName 查数据的表名
	 * @param objSourceField 对象源字段，即已有值字段，如user_id
	 * @param objTargetField 对象目标字段，即要取值字段，如user_name，一次性装多个字段用半角逗号(,)分割，个数需与tableReferenceField一致
	 * @param tableMatchingField 数据库表匹配字段，即数据库中对应对象中objSourceField字段，如id
	 * @param tableReferenceField 数据库表参照字段，即数据库中对应对象中objTargetField字段，如果name，一次性装多个字段用半角逗号(,)分割，个数需与objTargetField一致
	 * @param condition 外挂查询条件
	 */
	public static void assembly(Object obj,String tableName,String objSourceField,String objTargetField,String tableMatchingField,String tableReferenceField,String condition) throws Exception{
		//解析objTargetField与tableReferenceField，将按逗号(,)分割的字段分成数组
	    String objTargetFieldArray[] = objTargetField.split(",");
	    String tableReferenceFieldArray[] = objTargetField.split(",");
	    if(objTargetFieldArray.length!=tableReferenceFieldArray.length){
	        System.out.println("装配个数不一致");
	        return;
	    }
	    
		//生成get和set方法的字符串，要求参数中的大小写要严格与vo中的属性一致
		String methodSourceForGet = "get"+ objSourceField.substring(0,1).toUpperCase() + objSourceField.substring(1);
		String methodTargetForSet = "set"+ objTargetField.substring(0,1).toUpperCase() + objTargetField.substring(1);
		String methodTargetForGet = "get"+ objTargetField.substring(0,1).toUpperCase() + objTargetField.substring(1);
		
		//定义集合，如果要装配的对象是List，强制转换，如果不是，声明一个List，将对象放进List
		List objs = null;
		if(obj instanceof ArrayList){
		    objs=(List)obj;
		}
		else{
		    objs = new ArrayList();
		    objs.add(obj);
		}
		
		//切割现有vos，以适应sql的In中的长度
		List resObjs = splitList(objs);
		//Map用来存放最后的查询结果
		HashMap hmResult = new HashMap();
		for(int i=0 ; i<resObjs.size();i++){
			//分批进行查询
			List subVos = (List)resObjs.get(i);
			String findCondition = "''";
			//循环objs得到查询条件
			for(Iterator it = subVos.iterator() ; it.hasNext(); ){
				Object tempObj = (Object)it.next();
				//  判断类型
				String me="";
				//  Map
				if(tempObj instanceof Map){
					me = (String)((Map)tempObj).get(objSourceField);
				}
				//Vo
				else{
					me = (String)tempObj.getClass().getMethod(methodSourceForGet,new Class[]{}).invoke(tempObj,new Class[]{});
				}
				findCondition = findCondition+",'"+me+"'";
			}
			//  得到分批查询的结果，用putAll将结果放入最终结果的Map中
			HashMap hmResultSub = getAssemblyBs().findTargetBySource(findCondition,tableName,tableMatchingField,tableReferenceField,condition);
			hmResult.putAll(hmResultSub);
		}
		
		//  将结果放到vos的相应属性中
		for(int i=0;i<objs.size() ; i++){
			//  得到一对象
			Object tempObj = (Object)objs.get(i);
			//  判断类型
			String me="";
			//  Map
			if(tempObj instanceof Map){
				me = (String)((Map)tempObj).get(objSourceField);
				((Map)tempObj).put(objTargetField, hmResult.get(me)==null?"":hmResult.get(me));
			}
			//Vo
			else{
				//  得到相应属性的类型
				Class retClass = tempObj.getClass().getMethod(methodTargetForGet,new Class[]{}).getReturnType();
				//  由于基本类型要用到其包装类，在此定义resClass，当属性类型为基本类型的时候，resClass得到相应的包装类
				//  若不为基本类型，resClass同retClass
				Class resClass = null;
				if(LST_JAVA_TYPES.contains(retClass.toString())){
					resClass = Class.forName("java.lang."+retClass.toString().substring(0,1).toUpperCase() + retClass.toString().substring(1)+(retClass.toString().equals("int")?"eger":""));
				}else{
					resClass = retClass;
				}
				//  拿到结果Map的key值
				me = (String)tempObj.getClass().getMethod(methodSourceForGet,new Class[]{}).invoke(tempObj,new Class[]{});
				
				String strre = "";
				if (hmResult.get(me)!=null){
					//  拿到Map的value值，将其放入vo的相应属性中
					strre = (String)hmResult.get(me);
					//  将值set到相应属性中，其中属性的类型必须能用String类型构造。
					tempObj.getClass().getMethod(methodTargetForSet,new Class[]{retClass}).invoke(tempObj,new Object[]{resClass.getConstructor(new Class[]{String.class}).newInstance(new Object[]{strre})});
				}
			}   
		}
	}
}
