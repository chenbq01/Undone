package org.season.autumn.repository;

import org.season.autumn.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface UserRepository  extends Repository<User, Long> {
	
	public User save(User user);
	
	public void delete(Long id);
	
	@Query("from User u where u.username = :username")
	public User findByUsername(@Param("username") String username);

}
