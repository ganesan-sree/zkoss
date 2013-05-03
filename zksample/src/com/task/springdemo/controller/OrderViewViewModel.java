package com.task.springdemo.controller;

import java.util.List;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import com.task.springdemo.bean.CartItem;
import com.task.springdemo.bean.Order;
import com.task.springdemo.service.UserOrderManager;
/**
 * 
 * @author Ian Y.T Tsai(zanyking)
 *
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class OrderViewViewModel  {
	
	@WireVariable
	private UserOrderManager userOrderManager;
	
	private Order selectedItem;

	public Order getSelectedItem() {
		return selectedItem;
	}
	
	@NotifyChange("selectedItem")
	public void setSelectedItem(Order selectedItem) {
		this.selectedItem = selectedItem;
	}
	
	public List<Order> getOrders() {
		return userOrderManager.findAll();
	}
	
	@Command
	@NotifyChange({"orders", "selectedItem"})
	public void cancelOrder() {
		if (getSelectedItem() == null) {
			return;
		}
		userOrderManager.cancelOrder(getSelectedItem());
		setSelectedItem(null);
	}
	
	@GlobalCommand
	@NotifyChange("orders")
	public void submitNewOrder( 
			 @BindingParam("cartItems")List<CartItem> cartItems 
			,@BindingParam("orderNote") String orderNote){
		userOrderManager.createOrder( cartItems, orderNote);
	}
}
