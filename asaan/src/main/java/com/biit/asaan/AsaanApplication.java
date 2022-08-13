package com.biit.asaan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//implements CommandLineRunner
@SpringBootApplication
public class AsaanApplication {

//	@Autowired
//	private RoleService roleService;

	public static void main(String[] args) {
		SpringApplication.run(AsaanApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//		Role role = new Role();
//		role.setName("ROLE_USER");
//		roleService.creteRole(role);
//		Role role1 = new Role();
//		role1.setName("ROLE_ADMIN");
//		roleService.creteRole(role1);
//	}

}
