package com.task.springmvc.data.service;

import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.task.springmvc.data.bean.Product;


@Service
public class ProductDAO {

	private static List<Product> productList = Collections.synchronizedList(new LinkedList<Product>());
	private static int id = 1;
	private static Random rand = new Random();
	
	static {
		rand.setSeed(new Date().getTime());
		productList.add(new Product(id++, "Coffee", rand.nextInt(9999) + 100, "Coffee"));
		productList.add(new Product(id++, "Cookies", rand.nextInt(9999) + 100, "cokkies"));
		productList.add(new Product(id++, "Chocolate", rand.nextInt(9999) + 100, "chocolate"));
		productList.add(new Product(id++, "Milk", rand.nextInt(9999) + 100, "milk"));
		productList.add(new Product(id++, "Toast", rand.nextInt(9999) + 100, "toast"));
	}
	
	public ProductDAO() {}
	
	public List<Product> findAll() {
		return productList;
	}

}
