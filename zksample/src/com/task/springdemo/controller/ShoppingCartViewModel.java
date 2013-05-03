package com.task.springdemo.controller;

import java.util.HashMap;
import java.util.List;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import com.task.springdemo.bean.CartItem;
import com.task.springdemo.service.ShoppingCart;
/**
 * 
 * @author Ian Y.T Tsai(zanyking)
 *
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class ShoppingCartViewModel {
	
	private CartItem selectedItem;
	
	@WireVariable
	private ShoppingCart shoppingCart;
	
	public String getOrderNote() {
		return shoppingCart.getDescription();
	}

	public void setOrderNote(String orderNote) {
		shoppingCart.setDescription(orderNote);
	}

	public CartItem getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(CartItem selectedItem) {
		this.selectedItem = selectedItem;
	}

	public List<CartItem> getCartItems() {
		return shoppingCart.getItems();
	}
	
	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}
	
	@Command
	@NotifyChange({"cartItems", "shoppingCart", "orderNote"})
	public void submit() {
		HashMap<String, Object> args = new HashMap<String, Object>();
		args.put("cartItems", getCartItems());
		args.put("orderNote", getOrderNote());
		BindUtils.postGlobalCommand(null, null, "submitNewOrder", args);
		clearShoppingCart();
	}
	
	@Command
	@NotifyChange({"cartItems", "shoppingCart"})
	public void clearShoppingCart() {
		setOrderNote("");
		shoppingCart.clear();
	}
	
	@Command
	@NotifyChange({"cartItems", "shoppingCart"})
	public void removeCartitem(@BindingParam("cartItem") CartItem cartItem) {
		shoppingCart.remove(cartItem);
	}
	
	@GlobalCommand
	@NotifyChange({"cartItems", "shoppingCart"})
	public void updateShoppingCart() {
		//no post processing to be done
	}
	

}
