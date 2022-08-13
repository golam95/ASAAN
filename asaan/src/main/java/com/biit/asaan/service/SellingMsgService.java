package com.biit.asaan.service;

import java.util.List;
import com.biit.asaan.model.SellingMsg;

public interface SellingMsgService {
	
	List<SellingMsg> findAll();
	
	String saveSellingMsg(SellingMsg addMsg,String userName);
	
	int countUserBaseMsg(Long userId);
	
	void deleteSellingMsg(Long id);

	SellingMsg findByid(Long id);
	
	void updateStatus(SellingMsg update);
	
	Long count();

}
