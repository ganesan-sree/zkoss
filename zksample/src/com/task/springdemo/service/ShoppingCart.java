/**
 * 
 */
package com.task.springdemo.service;

import java.util.List;

import com.task.springdemo.bean.CartItem;
import com.task.springdemo.bean.Product;
import com.task.springdemo.web.OverQuantityException;



/**
 * @author Ian YT Tsai(zanyking)
 *
 */
public interface ShoppingCart {

	public String getDescription();
	public void setDescription(String description);
	
	public List<CartItem> getItems();
	public void add(Product prod, int amount) throws OverQuantityException;
	public void remove(CartItem cartItem);
	public void clear();
	public float getTotalPrice();
}
