package cn.com.uwoa.global.security;

import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

import org.apache.solr.common.SolrDocument;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;

import cn.com.uwoa.business.authorize.po.Users;
import cn.com.uwoa.global.security.po.CustomerUserDetails;
import cn.com.uwoa.system.frame.po.FieldPo;

public class SecurityHelper {

	/**
	 * 得到当前登陆用户
	 * @return
	 */
	public static Users getLoginUser()
	{
		//return 
		Users users = new Users();
		users.setId("0093B2E4F0634DE080CBBF1EAF8FE078");
		return users;
	}
	public static boolean isLogin(){
		try{
			CustomerUserDetails userDetails = (CustomerUserDetails) SecurityContextHolder.getContext()
				    .getAuthentication()
				    .getPrincipal();
		}
		catch(Exception e)
		{
			return false;
		}
		return true;
	
	}
	/**
	 * 得到当前登陆用户ID
	 * @return
	 */
	public static String getLoginUserId()
	{
		
		CustomerUserDetails userDetails = (CustomerUserDetails) SecurityContextHolder.getContext()
			    .getAuthentication()
			    .getPrincipal();
		return userDetails.getId();
		
		
		
		
	}
	
	public static boolean HasAuthority(String url){
		CustomerUserDetails userDetails = (CustomerUserDetails) SecurityContextHolder.getContext()
			    .getAuthentication()
			    .getPrincipal();
		Set<GrantedAuthority> al = (Set)userDetails.getAuthorities();
		Iterator<GrantedAuthority> it = al.iterator();
		while (it.hasNext()) {
			GrantedAuthority ga = it.next();
			if(ga.getAuthority().equalsIgnoreCase(url)){
				return true;				
			}
		}
		return false;
		
	}
	
	public static String getRestId(){
		CustomerUserDetails userDetails = (CustomerUserDetails) SecurityContextHolder.getContext()
			    .getAuthentication()
			    .getPrincipal();
		return userDetails.getRestId();
		//return "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
	}
	
	public static boolean getIsGroup(){
		return true;
	}
}
