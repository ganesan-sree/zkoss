package com.task.springdemo.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.task.springdemo.bean.CartItem;
import com.task.springdemo.bean.Product;
import com.task.springdemo.web.OverQuantityException;


/**
 * This is an in-memory approach of Shopping Cart implementation.<br>
 * In this case, shopping cart won't be stored in the 
 * @author Ian Y.T Tsai(zanyking)
 */
@Component("shoppingCart")
@Scope("session")
public class ShoppingCartManager implements ShoppingCart{
	
	@Autowired
	private UserCredentialManager userCredentialManager;

	private static final long serialVersionUID = 464821961483850854L;

	private Map<Long, CartItem> items = 
		Collections.synchronizedMap(new LinkedHashMap<Long, CartItem>());

	private String description;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public List<CartItem> getItems() {
		return new ArrayList<CartItem>(items.values());
	}

	private CartItem getItem(long prodId) {
		return items.get(prodId);
	}

	private void add(CartItem item) {
		items.put(item.getProduct().getId(), item);
	}

	public void add(Product prod, int amount) throws OverQuantityException {

		CartItem item = this.getItem(prod.getId());
		validate(item, prod, amount);
		if (item == null) {
			this.add(item = new CartItem(
					userCredentialManager.getUser().getId(), prod));
			
			item.add(amount);
		} else {
			item.add(amount);
		}
	}

	private static void validate(CartItem item, Product prod, int amount)
			throws OverQuantityException {
		int oriAmount = item == null ? 0 : item.getAmount();
		int total = oriAmount + amount;
		if (total > prod.getQuantity()) {
			String errMesg = "too much: " + oriAmount + " + " + amount + " > "
					+ prod.getQuantity();
			throw new OverQuantityException(errMesg);
		}
	}

	public void remove(CartItem cartItem) {
		items.remove(cartItem.getProduct().getId());
	}

	public void clear() {
		items.clear();
	}

	public float getTotalPrice() {
		float subTotal = 0;
		for (CartItem item : items.values()) {
			subTotal += item.getProduct().getPrice() * item.getAmount();
		}
		return subTotal;
	}

}
