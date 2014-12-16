package org.season.autumn.service;

import java.util.List;

import org.season.autumn.domain.Permission;

public interface PermissionService {

	public List<Permission> findPermissionByUrl(String url);

}
