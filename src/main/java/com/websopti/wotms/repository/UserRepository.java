package com.websopti.wotms.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.websopti.wotms.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	
	User findOneByEmail(String email);

	@Modifying
	@Query("UPDATE User SET name = ?2 WHERE id = ?1")
	void updateName(Long id, String name);

	@Modifying
	@Query("UPDATE User SET password = ?2 WHERE id = ?1")
	void updatePassword(Long id, String password);
}
