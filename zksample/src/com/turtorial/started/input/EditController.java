package com.turtorial.started.input;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Doublebox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Spinner;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;


public class EditController extends SelectorComposer<Component> {
	private static final long serialVersionUID = 1L;

	private CarServiceImpl carService = new CarServiceImpl();
	private InventoryItem inventoryItem = new InventoryItem("1", "The Tumbler", new Date(), 654321, 29999.9,
			30, "Southern Depository");
	@Wire
	private Window win;
	@Wire
	private Textbox titleTextbox;
	@Wire
	private Datebox createdDatebox;
	@Wire
	private Intbox barcodeIntbox;
	@Wire
	private Doublebox unitPriceDoublebox;
	@Wire
	private Spinner quantitySpinner;
	@Wire
	private Textbox locationTextbox;

	public InventoryItem getInventoryItem() {
		return inventoryItem;
	}

	@Listen("onChange = #titleTextbox")
	public void changeTitle() {
		String title = titleTextbox.getValue();
		inventoryItem.setTitle(title);

		showNotify("Changed to: " + title, titleTextbox);
	}

	@Listen("onChange = #createdDatebox")
	public void changeCreatedDate() {
		Date createdDate = createdDatebox.getValue();
		inventoryItem.setCreatedDate(createdDate);
		DateFormat formatter = new SimpleDateFormat(createdDatebox.getFormat());

		showNotify("Changed to: " + formatter.format(createdDate), createdDatebox);
	}

	@Listen("onChange = #barcodeIntbox")
	public void changeBarcode() {
		Integer barcode = barcodeIntbox.getValue();
		inventoryItem.setBarcode(barcode);
		NumberFormat formatter = new DecimalFormat(barcodeIntbox.getFormat());

		showNotify("Changed to: " + formatter.format(barcode), barcodeIntbox);
	}

	@Listen("onChange = #unitPriceDoublebox")
	public void changeUnitPrice() {
		Double unitPrice = unitPriceDoublebox.getValue();
		inventoryItem.setUnitPrice(unitPrice);
		NumberFormat formatter = new DecimalFormat(unitPriceDoublebox.getFormat());

		showNotify("Changed to: " + formatter.format(unitPrice), unitPriceDoublebox);
	}

	@Listen("onChange = #quantitySpinner")
	public void changeQuantity() {
		Integer quantity = quantitySpinner.getValue();
		inventoryItem.setQuantity(quantity);
		NumberFormat formatter = new DecimalFormat(quantitySpinner.getFormat());

		showNotify("Changed to: " + formatter.format(quantity), quantitySpinner);
	}

	@Listen("onChange = #locationTextbox")
	public void changeLocation() {
		String location = locationTextbox.getValue();
		inventoryItem.setLocation(location);

		showNotify("Changed to:" + location, locationTextbox);
	}

	@Listen("onClick = #submitButton")
	public void submit() {
		carService.store(inventoryItem);

		showNotify("Saved", win);
	}

	private void showNotify(String msg, Component ref) {
		Clients.showNotification(msg, "info", ref, "end_center", 2000);
	}
}
