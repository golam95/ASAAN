package com.biit.asaan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.biit.asaan.model.ContactInfo;

@Repository 
public interface ContactInfoRepository extends JpaRepository<ContactInfo, Long>{
	
	ContactInfo findByid(Long id);
}
