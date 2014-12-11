package org.season.autumn.springsecurity.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.memory.InMemoryDaoImpl;

public class InMemoryChangePasswordDaoImpl extends InMemoryDaoImpl implements
		IChangePassword {

	@Override
	public void changePassword(String username, String password) {
		 // get the UserDetails    
	    User userDetails =     
	        (User) getUserMap().getUser(username);    
	    // create a new UserDetails with the new password    
	    User newUserDetails =     
	        new User(userDetails.getUsername(),password,    
	        userDetails.isEnabled(),     
	        userDetails.isAccountNonExpired(),    
	      userDetails.isCredentialsNonExpired(),    
	        userDetails.isAccountNonLocked(),    
	        userDetails.getAuthorities());    
	    // add to the map    
	    getUserMap().addUser(newUserDetails);    

	}

}
