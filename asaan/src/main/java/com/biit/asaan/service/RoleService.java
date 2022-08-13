package com.biit.asaan.service;

import com.biit.asaan.model.Role;

public interface RoleService {
	
	Role findByname(String theRoleName);
	
	void creteRole(Role role);

}
