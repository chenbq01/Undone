package cn.com.uwoa.global.security;

import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.beans.factory.annotation.Qualifier;  
import org.springframework.security.authentication.encoding.PasswordEncoder;  
import org.springframework.stereotype.Component;   

@Component("MyPasswordEncode")  
public class MyPasswordEncode implements PasswordEncoder {       
	      
	/**       * 这个方法供下面的方法isPasswordValid 调用 是用来进行盐加密的       * */     
	public String encodePassword(String rawPass, Object salt) {  
		
		//String salted = rawPass + "{" + salt.toString() + "}";          
		//System.out.println(salted);          
		return cn.com.uwoa.system.tools.UUID.Md5(rawPass);      
	}      

	public boolean isPasswordValid(String encPass, String rawPass, Object salt)
	{           
		if (encPass.equals(encodePassword(rawPass, salt))) 
		{               
			return true;          
		}          
		return false;      
	}   
} 




