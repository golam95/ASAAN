package com.biit.asaan.serviceImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.biit.asaan.model.CartItem;
import com.biit.asaan.model.User;
import com.biit.asaan.repository.CartItemRepository;
import com.biit.asaan.repository.UserRepository;
import com.biit.asaan.service.CartItemService;

@Service
public class CartItemServiceImpl implements CartItemService {

	@Autowired
	private CartItemRepository cartItemRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<CartItem> findByuserId(Long userId) {
		return cartItemRepository.findByuserId(userId);
	}

	@Override
	public String addToCart(CartItem saveCart, String userName) {
		User getUser = userRepository.findByuserName(userName);
		String status = "";
		if (getUser != null) {
			saveCart.setUserId(getUser.getId());
			saveCart.setUserName(getUser.getUserName());
			status = "1";
		}
		cartItemRepository.save(saveCart);
		return status;
	}

	@Override
	public void deleteCart(Long id) {
		cartItemRepository.deleteById(id);
	}

}
