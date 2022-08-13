package com.biit.asaan.serviceImpl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.biit.asaan.model.ContactInfo;
import com.biit.asaan.repository.ContactInfoRepository;
import com.biit.asaan.service.ContactInfoService;

@Service
public class ContactInfoServiceImpl implements ContactInfoService {

	@Autowired
	private ContactInfoRepository contactRepository;

	@Override
	public void saveContactInfo(ContactInfo saveContact) {
		saveContact.setDate(new Date());
		saveContact.setStatus("1");
		contactRepository.save(saveContact);
	}

	@Override
	public List<ContactInfo> getList() {
		return contactRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
	}

	@Override
	public void deleteContactInfo(Long id) {
		contactRepository.deleteById(id);
	}

	@Override
	public ContactInfo findByid(Long contactId) {
		return contactRepository.findByid(contactId);
	}

	@Override
	public void updateStatus(ContactInfo saveContact) {
		ContactInfo conInfo = contactRepository.findByid(saveContact.getId());
		ContactInfo conInfo1 = new ContactInfo();
		if (conInfo != null) {
			conInfo1 = contactRepository.findByid(saveContact.getId());
			conInfo1.setStatus("0");
		}
		contactRepository.save(conInfo1);
	}

	@Override
	public Long count() {
		return contactRepository.count();
	}

}
