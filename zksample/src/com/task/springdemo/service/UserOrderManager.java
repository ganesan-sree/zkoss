/**
 * 
 */
package com.task.springdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.task.springdemo.bean.CartItem;
import com.task.springdemo.bean.Order;
import com.task.springdemo.dao.OrderDemoDAO;


/**
 * @author Ian YT Tsai (Zanyking)
 *
 */
@Component
@Scope("session")
public class UserOrderManager{

	@Autowired
	private OrderDemoDAO orderDao;
	@Autowired
	private UserCredentialManager userCredentialManager;

	public List<Order> findAll() {
		return orderDao.findByUser(userCredentialManager.getUser());
	}

	public Order cancelOrder(Order order) {
		return orderDao.cancelOrder(order);
	}

	public Order createOrder(List<CartItem> cartItems, String orderNote) {
		return orderDao.createOrder(userCredentialManager.getUser(), cartItems, orderNote);
	}
	
}
