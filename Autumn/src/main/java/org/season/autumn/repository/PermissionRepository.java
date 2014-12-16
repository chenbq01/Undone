package org.season.autumn.repository;

import java.util.List;

import org.season.autumn.domain.Permission;
import org.springframework.data.repository.Repository;

public interface PermissionRepository extends Repository<Permission, Long> {

	public Permission save(Permission permission);

	void delete(Long id);

	public List<Permission> findAll();

}
