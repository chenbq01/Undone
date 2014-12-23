package cn.com.uwoa.system.validate;

import java.util.Iterator;
import java.util.Map;

/**
 * 数据字典 - 帮助类
 * @author liyue
 */
public class ValidateHelper {
	/**
	 * 校验
	 * @param validate 校验信息
	 * @return 校验结果
	 */
	public static String checkValidate(Map validate){
		String errorInfo = "";
		String subErrorInfo = "";
		if(validate.get("sub")!=null){
			Iterator subIt = ((Map)validate.get("sub")).values().iterator();
			while(subIt.hasNext()){
				subErrorInfo+=checkValidate((Map)subIt.next());
			}
		}
		
		Iterator it = validate.values().iterator();
		while(it.hasNext()){
			Map t = (Map)it.next();
			if(t.get("validate")==null){
				continue;
			}
			String[] validateKey = t.get("validate").toString().split(";");
			for(int i=0;i<validateKey.length;i++){
				if(validateKey[i].equals("notNull")){
					if(t.get("value")==null || t.get("value").equals("")){
						errorInfo+="“"+t.get("inputName")+"”不能为空<br>";
					}
				}
				else if(validateKey[i].equals("only")){
					
				}
			}
		}
		errorInfo+=subErrorInfo;
		
		return errorInfo;
	}
}
