package com.biit.asaan.serviceImpl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.biit.asaan.helper.SendEmail;
import com.biit.asaan.model.BuyingProduct;
import com.biit.asaan.model.CartItem;
import com.biit.asaan.model.ContactInfo;
import com.biit.asaan.model.Order;
import com.biit.asaan.model.User;
import com.biit.asaan.repository.BuyingProductRepository;
import com.biit.asaan.repository.CartItemRepository;
import com.biit.asaan.repository.OrderRepository;
import com.biit.asaan.repository.UserRepository;
import com.biit.asaan.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private CartItemRepository carItemRepository;

	@Autowired
	private BuyingProductRepository buyingProductRepository;

	@Autowired
	private SendEmail sendEmail;

	@Override
	@Transactional
	public String addOrder(Order saveOrder, String userName) {
		User getUser = userRepository.findByuserName(userName);
		String status = "";
		if (getUser != null) {
			saveOrder.setUserId(getUser.getId());
			saveOrder.setUserName(getUser.getUserName());
			saveOrder.setStatus("1");
			saveOrder.setDate(new Date());
			status = "1";
			for (CartItem cart : carItemRepository.findByuserId(getUser.getId())) {
				BuyingProduct buyingpro = buyingProductRepository.findByid(cart.getProductId());
				double buyProductQty = buyingpro.getGovtPrice();
				int carQuantity = cart.getQuantity();
				int totalQuanity = (int) (buyProductQty - carQuantity);
				buyingpro.setGovtPrice(totalQuanity);
				buyingProductRepository.save(buyingpro);
			}
		}
		String Info = "e-ASAAN Management System" + "\n\n";
		Info += "Hey, " + getUser.getFirstname() + " "
				+ "hopefully you are doing well.Thank you for buying us to do something from here!!" + "\n";
		Info += "Your shoping details:" + "\n";
		Info += "Products: " + saveOrder.getProductName() + "\n";
		Info += "Total Quantity: " + saveOrder.getQuantity() + "\n";
		Info += "Total Price: " + saveOrder.getProductPrice() + "Tk." + "\n";
		Info += "Your shoping details:" + "\n";
		Info += "If you have any problem.Please contact us" + "\n";
		Info += "Phone: +88 001111" + "\n";
		Info += "Gmail: abc@gmail.com";
		sendEmail.send(getUser.getEmail(), "Forgot Password", Info, "", "");
		orderRepository.save(saveOrder);
		carItemRepository.deleteByuserId(getUser.getId());
		return status;
	}

	@Override
	public List<Order> findByuserId(Long userId) {
		return orderRepository.findByuserId(userId);
	}

	@Override
	public List<Order> getOrderList() {
		return orderRepository.findAll();
	}

	@Override
	public void deleteOrder(Long id) {
		orderRepository.deleteById(id);
	}

	@Override
	public Order findByid(Long id) {
		return orderRepository.findByid(id);
	}

	@Override
	public void updateStatus(Order update) {
		Order orderInfo = orderRepository.findByid(update.getId());
		Order orderInfo1 = new Order();
		if (orderInfo != null) {
			orderInfo1 = orderRepository.findByid(update.getId());
			orderInfo1.setStatus("0");
		}
		orderRepository.save(orderInfo1);
	}

	@Override
	public Long count() {
		return orderRepository.count();
	}

}
