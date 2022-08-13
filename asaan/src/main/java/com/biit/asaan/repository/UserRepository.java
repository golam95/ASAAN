package com.biit.asaan.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.biit.asaan.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByuserName(String userName);

	User findByemail(String useremail);

	User findByphone(String userphone);

	List<User> findByrolename(String roleName);

}
