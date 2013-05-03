/**
 * 
 */
package com.task.springdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.springdemo.bean.Product;
import com.task.springdemo.dao.ProductDemoDAO;


/**
 * @author Ian YT Tsai (Zanyking)
 *
 */
@Service
public class ProductManager {
	
	@Autowired
	private ProductDemoDAO productDao;

	public List<Product> findAllAvailable() {
		return productDao.findAllAvailable();
	}
}
