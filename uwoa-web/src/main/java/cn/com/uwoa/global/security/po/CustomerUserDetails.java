package cn.com.uwoa.global.security.po;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class CustomerUserDetails extends org.springframework.security.core.userdetails.User { 

	private static final long serialVersionUID = -25559580612205393L; 

	private String id; 
	
	private String rest_id;

	public CustomerUserDetails(String id,String rest_id, String username, String password,boolean enabled, boolean accountNonExpired,  boolean credentialsNonExpired, boolean accountNonLocked,  Collection<GrantedAuthority>  authorities) { 
		super(username, password, enabled, accountNonExpired, 
				credentialsNonExpired, accountNonLocked, authorities); 
			this.id = id; 
			this.rest_id = rest_id;
	} 
	
	public String getId() { 
		return this.id; 
	} 
	public void setId(String id) { 
		this.id = id; 
	} 
	public String getRestId() { 
		return this.rest_id; 
	} 
	public void setRestId(String rest_id) { 
		this.rest_id = rest_id; 
	} 
} 
