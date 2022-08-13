package com.biit.asaan.service;

import java.util.List;
import com.biit.asaan.model.Order;

public interface OrderService {

	String addOrder(Order saveOrder, String userName);

	List<Order> findByuserId(Long userId);

	List<Order> getOrderList();

	void deleteOrder(Long id);

	Order findByid(Long id);

	void updateStatus(Order update);
	
	Long count();

}
