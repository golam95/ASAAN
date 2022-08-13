package com.biit.asaan.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.biit.asaan.model.FarmerSchedule;

@Repository
public interface FarmerScheduleRepository extends JpaRepository<FarmerSchedule, Long> {

	List<FarmerSchedule> findByname(String userId);

	@Query(value = "SELECT * FROM farmerschedule where schudle_date=?1 and schedule_time=?2", nativeQuery = true)
	FarmerSchedule fetctScheduleList(String scduleDate, String time);

	@Query(value = "SELECT COUNT(id) FROM farmerschedule WHERE user_id=?1", nativeQuery = true)
	int countfarmerschedule(Long userId);
	


}
