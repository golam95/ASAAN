package com.biit.asaan.service;

import java.util.List;
import com.biit.asaan.model.ContactInfo;

public interface ContactInfoService {

	void saveContactInfo(ContactInfo saveContact);

	List<ContactInfo> getList();

	void deleteContactInfo(Long id);

	ContactInfo findByid(Long contactId);
	
	void updateStatus(ContactInfo saveContact);
	
	Long count();

}
