package com.task.springmvc.view.zk;

import java.util.List;

import org.zkoss.bind.annotation.ExecutionParam;
import org.zkoss.bind.annotation.Init;

import com.task.springmvc.data.bean.Product;


public class ProductViewModel {
	
	private List<Product> productList;
	
	@Init
	public void init(@ExecutionParam("productList") List<Product> productList) {
		this.productList = productList;
	}
	
	public List<Product> getProductList() {
		return productList;
	}
}
