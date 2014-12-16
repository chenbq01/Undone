package org.season.autumn.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.season.autumn.domain.Permission;
import org.season.autumn.repository.PermissionRepository;
import org.season.autumn.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

@Service
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	private PermissionRepository permissionRepository;

	@Override
	public List<Permission> findPermissionByUrl(String url) {
		List<Permission> permlist = permissionRepository.findAll();
		List<Permission> perms = new ArrayList<Permission>();
		for (Permission perm : permlist) {
			if (urlMatcher(perm.getUrl(), url))
				perms.add(perm);
		}
		return perms;
	}

	private boolean urlMatcher(String permstr, String requestUri) {
		boolean isMatcher = false;
		PathMatcher matcher = new AntPathMatcher();
		isMatcher = matcher.match(permstr, requestUri);
		return isMatcher;
	}

}
