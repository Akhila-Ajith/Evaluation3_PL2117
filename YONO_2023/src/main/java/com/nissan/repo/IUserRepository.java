package com.nissan.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nissan.model.Users;
@Repository
public interface IUserRepository extends CrudRepository<Users, Integer> {
	@Query("from Users WHERE userName=?1 AND password=?2 AND roleId=1")
	public Users findAdminByUserNameAndPassword(String userName, String password);

	// custom method
	@Query("from Users WHERE userName=?1 AND password=?2 and roleId=2")
	public Users findCustomerByUserNameAndPassword(String userName, String password);
}

