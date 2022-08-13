package com.biit.asaan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.biit.asaan.model.SellingMsg;

@Repository
public interface SellingMsgRepository extends JpaRepository<SellingMsg, Long> {

	@Query(value = "SELECT COUNT(id) FROM sellingmsg WHERE user_id=?1", nativeQuery = true)
	int countUserBaseMsg(Long userId);

	SellingMsg findByid(Long id);

}
