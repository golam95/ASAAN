package com.biit.asaan.serviceImpl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.biit.asaan.model.SellingMsg;
import com.biit.asaan.model.User;
import com.biit.asaan.repository.SellingMsgRepository;
import com.biit.asaan.repository.UserRepository;
import com.biit.asaan.service.SellingMsgService;

@Service
public class SellingMsgServiceImpl implements SellingMsgService {

	@Autowired
	private SellingMsgRepository sellingMsgRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<SellingMsg> findAll() {
		return sellingMsgRepository.findAll();
	}

	@Override
	public String saveSellingMsg(SellingMsg addMsg, String userName) {
		User getUser = userRepository.findByuserName(userName);
		String status = "";
		if (getUser != null) {
			addMsg.setUserId(getUser.getId());
			addMsg.setEmail(getUser.getEmail());
			addMsg.setStatus("1");
			addMsg.setDate(new Date());
			status = "1";
		}
		sellingMsgRepository.save(addMsg);
		return status;
	}

	@Override
	public int countUserBaseMsg(Long userId) {
		return sellingMsgRepository.countUserBaseMsg(userId);
	}

	@Override
	public void deleteSellingMsg(Long id) {
		sellingMsgRepository.deleteById(id);
	}

	@Override
	public SellingMsg findByid(Long id) {
		return sellingMsgRepository.findByid(id);
	}

	@Override
	public void updateStatus(SellingMsg update) {
		SellingMsg sellInfo = sellingMsgRepository.findByid(update.getId());
		SellingMsg sellInfo1 = new SellingMsg();
		if (sellInfo != null) {
			sellInfo1 = sellingMsgRepository.findByid(update.getId());
			sellInfo1.setStatus("0");
		}
		sellingMsgRepository.save(sellInfo1);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return sellingMsgRepository.count();
	}

}
