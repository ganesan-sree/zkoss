package com.turtorial.started.selection_dropdown;

import java.util.Date;

public class InventoryItem {

	private String inventoryId;
	private String title;
	private Date createdDate;
	private int barcode;
	private double unitPrice;
	private int quantity;
	private String location;

	public InventoryItem(String inventoryId, String title, Date createdDate, int barcode, double unitPrice, int quantity, String location) {
		this.inventoryId = inventoryId;
		this.title = title;
		this.createdDate = createdDate;
		this.barcode = barcode;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
		this.location = location;
	}

	public String getInventoryId() {
		return inventoryId;
	}

	public void setInventoryId(String inventoryId) {
		this.inventoryId = inventoryId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public int getBarcode() {
		return barcode;
	}

	public void setBarcode(int barcode) {
		this.barcode = barcode;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	
	@Override
	public String toString() {
	
		return this.getInventoryId()+"\n"+"" +
				""+this.getLocation();
		
	}

}
