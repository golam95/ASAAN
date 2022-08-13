package com.biit.asaan.service;

import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;
import com.biit.asaan.model.User;

public interface UserService extends UserDetailsService {

	User findByUserName(String userName);

	void save(User user);

	void saveUser(User user);

	String resetPassword(User user);

	List<User> getList();

	List<User> findByrolename(String roleName);

	User findByemail(String useremail);

	User findByphone(String userphone);

	void updateUserProfile(User user, String userName);

	Long count();

	void deleteUser(Long id);

}
