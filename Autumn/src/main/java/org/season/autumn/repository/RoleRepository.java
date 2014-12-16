package org.season.autumn.repository;

import org.season.autumn.domain.Role;
import org.springframework.data.repository.Repository;

public interface RoleRepository extends Repository<Role, Long> {

	public Role save(Role role);

	void delete(Long id);

}
