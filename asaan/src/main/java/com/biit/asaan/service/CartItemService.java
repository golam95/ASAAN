package com.biit.asaan.service;

import java.util.List;

import com.biit.asaan.model.CartItem;

public interface CartItemService {

	List<CartItem> findByuserId(Long userId);

	String addToCart(CartItem saveCart, String userName);

	void deleteCart(Long id);

}
