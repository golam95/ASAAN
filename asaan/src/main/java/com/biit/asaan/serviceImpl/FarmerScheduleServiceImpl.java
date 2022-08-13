package com.biit.asaan.serviceImpl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.biit.asaan.model.FarmerSchedule;
import com.biit.asaan.model.User;
import com.biit.asaan.repository.FarmerScheduleRepository;
import com.biit.asaan.repository.UserRepository;
import com.biit.asaan.service.FarmerScheduleService;

@Service
public class FarmerScheduleServiceImpl implements FarmerScheduleService {

	@Autowired
	private FarmerScheduleRepository farmerScheduleRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public String saveFarmerSchedule(FarmerSchedule addSchedule, String userName) {
		User getUser = userRepository.findByuserName(userName);
		String status = "";
		if (getUser != null) {
			addSchedule.setUserId(getUser.getId());
			addSchedule.setName(getUser.getUserName());
			addSchedule.setEmail(getUser.getEmail());
			addSchedule.setStatus("1");
			addSchedule.setDate(new Date());
			status = "1";
		}
		farmerScheduleRepository.save(addSchedule);
		return status;
	}

	@Override
	public FarmerSchedule fetctScheduleList(String scduleDate, String time) {
		return farmerScheduleRepository.fetctScheduleList(scduleDate, time);
	}

	@Override
	public List<FarmerSchedule> findByname(String userId) {
		return farmerScheduleRepository.findByname(userId);
	}

	@Override
	public int countfarmerschedule(Long userId) {
		return farmerScheduleRepository.countfarmerschedule(userId);
	}

	@Override
	public List<FarmerSchedule> findAll() {
		return farmerScheduleRepository.findAll();
	}

	@Override
	public Long count() {
		return farmerScheduleRepository.count();
	}

}
