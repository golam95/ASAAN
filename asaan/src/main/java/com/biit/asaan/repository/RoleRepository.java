package com.biit.asaan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.biit.asaan.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByname(String theRoleName);

}
