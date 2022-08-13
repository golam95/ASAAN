package com.biit.asaan.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.biit.asaan.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	List<Order> findByuserId(Long userId);
	
	Order findByid(Long id);

}
