package org.season.autumn.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.season.autumn.domain.Permission;
import org.season.autumn.domain.Role;
import org.season.autumn.service.PermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

public class CustomFilterInvocationSecurityMetadataSource implements
		FilterInvocationSecurityMetadataSource {

	private static final Logger logger = LoggerFactory
			.getLogger(CustomFilterInvocationSecurityMetadataSource.class);

	@Autowired
	private PermissionService permissionService;

	@Override
	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {
		String url = ((FilterInvocation) object).getRequestUrl();
		int firstQuestionMarkIndex = url.indexOf("?");
		if (firstQuestionMarkIndex != -1) {
			url = url.substring(0, firstQuestionMarkIndex);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("请求的地址为[" + url + "]");
		}
		List<ConfigAttribute> result = new ArrayList<ConfigAttribute>();
		ConfigAttribute attribute = new SecurityConfig("ROLE_GUEST");
		result.add(attribute);
		try {
			List<Permission> permissionList = permissionService
					.findPermissionByUrl(url);
			if (permissionList != null && permissionList.size() > 0) {
				for (Permission permission : permissionList) {
					Set<Role> roles = permission.getRoles();
					if (roles != null && roles.size() > 0) {
						for (Role role : roles) {
							ConfigAttribute conf = new SecurityConfig(
									role.getRolename());
							result.add(conf);
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return new ArrayList<ConfigAttribute>();
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

}
