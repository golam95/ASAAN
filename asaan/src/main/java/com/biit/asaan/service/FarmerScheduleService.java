package com.biit.asaan.service;

import java.util.List;
import com.biit.asaan.model.FarmerSchedule;

public interface FarmerScheduleService {

	List<FarmerSchedule> findByname(String userId);

	String saveFarmerSchedule(FarmerSchedule addSchedule, String userName);

	FarmerSchedule fetctScheduleList(String scduleDate, String time);
	
	int countfarmerschedule(Long userId);
	
	List<FarmerSchedule> findAll();
	
	Long count();
}
