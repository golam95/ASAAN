package com.biit.asaan.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.biit.asaan.model.Role;
import com.biit.asaan.repository.RoleRepository;
import com.biit.asaan.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Role findByname(String theRoleName) {
		return roleRepository.findByname(theRoleName);
	}

	@Override
	public void creteRole(Role role) {
		roleRepository.save(role);
	}

}
