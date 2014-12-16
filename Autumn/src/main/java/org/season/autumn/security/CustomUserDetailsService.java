package org.season.autumn.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import org.season.autumn.domain.Role;
import org.season.autumn.domain.User;
import org.season.autumn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		Collection<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("不存在用户名为[" + username + "]的用户");
		}

		Set<Role> roles = user.getRoles();
		for (Role role : roles) {
			auths.add(new SimpleGrantedAuthority(role.getRolename()));
		}

		return new org.springframework.security.core.userdetails.User(
				user.getUsername(), user.getPassword(), user.isEnabled(),
				user.isAccountNonExpired(), user.isCredentialsNonExpired(),
				user.isAccountNonLocked(), auths);

	}

}
