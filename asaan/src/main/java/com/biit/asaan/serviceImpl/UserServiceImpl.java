package com.biit.asaan.serviceImpl;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.biit.asaan.helper.SendEmail;
import com.biit.asaan.model.Role;
import com.biit.asaan.model.User;
import com.biit.asaan.repository.RoleRepository;
import com.biit.asaan.repository.UserRepository;
import com.biit.asaan.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private SendEmail sendEmail;

	@Override
	public User findByUserName(String userName) {
		return userRepository.findByuserName(userName);
	}

	@Override
	public void save(User user) {
		Long rowCount = userRepository.count();
		if (rowCount == 0) {
			user.setDate(new Date());
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			user.setRolename("ROLE_ADMIN");
			user.setRoles(Arrays.asList(roleRepository.findByname("ROLE_ADMIN")));
		} else {
			user.setDate(new Date());
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			user.setRolename("ROLE_USER");
			user.setRoles(Arrays.asList(roleRepository.findByname("ROLE_USER")));
		}
		userRepository.save(user);
	}

	@Override
	public List<User> getList() {
		return userRepository.findAll();
	}

	@Override
	public List<User> findByrolename(String roleName) {
		return userRepository.findByrolename(roleName);
	}

	@Override
	public void saveUser(User user) {
		user.setDate(new Date());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRolename("ROLE_ADMIN");
		user.setRoles(Arrays.asList(roleRepository.findByname("ROLE_ADMIN")));
		user.setCanAdd(user.getCanAdd() == null ? "0" : "1");
		user.setCanDelete(user.getCanDelete() == null ? "0" : "1");
		user.setCanUpdate(user.getCanUpdate() == null ? "0" : "1");
		userRepository.save(user);
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByuserName(username);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

	@Override
	public User findByemail(String useremail) {
		return userRepository.findByemail(useremail);
	}

	@Override
	public User findByphone(String userphone) {
		return userRepository.findByphone(userphone);
	}

	@Override
	public void updateUserProfile(User user, String userName) {
		User user1 = new User();
		user1 = userRepository.findByuserName(userName);
		user1.setFirstname(user.getFirstname());
		user1.setEmail(user.getEmail());
		user1.setPhone(user.getPhone());
		if (!user.getPassword().equals("")) {
			user1.setPassword(passwordEncoder.encode(user.getPassword()));
		}
		userRepository.save(user1);
	}

	@Override
	public String resetPassword(User user) {
		User checkUser = userRepository.findByemail(user.getEmail());
		User user1 = new User();
		String status = "";
		if (checkUser != null) {
			String validEmail = user.getEmail();
			int rnd = (int) (Math.random() * 100);
			String rndstring = Integer.toString(rnd);
			String getstore = validEmail.substring(0, 7);
			String passwordAssistance = getstore + rndstring;
			String Info = "e-ASAAN Management System" + "\n\n";
			Info += "Hey, " + user.getFirstname() + " "
					+ "hopefully you are doing well.Thank you so much for contacting us!!" + "\n";
			Info += "Your new password: " + passwordAssistance + "\n\n\n";
			Info += "If you have any problem.Please contact us" + "\n";
			Info += "Phone: +88 001111" + "\n";
			Info += "Gmail: abc@gmail.com";
			user1 = userRepository.findByemail(user.getEmail());
			user1.setPassword(passwordEncoder.encode(passwordAssistance));
			userRepository.save(user1);
			sendEmail.send(validEmail, "Forgot Password", Info, "", "");
			status = "1";
		}
		return status;
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return userRepository.count();
	}

	@Override
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}

}
