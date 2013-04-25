package com.turtorial.started.selection_dropdown;

import java.util.Set;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.CheckEvent;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Selectbox;
import org.zkoss.zul.Window;


public class EditController extends SelectorComposer<Component> {
	private static final long serialVersionUID = 1L;

	private CarServiceImpl carService = new CarServiceImpl();
	private Car car;
	private ListModel<String> typesModel = new ListModelList<String>(CarData.getTypes());
	private ListModel<String> countriesModel = new ListModelList<String>(CarData.getCountries());
	private ListModel<String> salesmenModel = new ListModelList<String>(CarData.getSalesmen());

	@Wire
	private Window win;
	@Wire
	private Radiogroup transmissionRadiogroup;
	@Wire
	private Checkbox absCheckbox;
	@Wire
	private Checkbox airbagCheckbox;
	@Wire
	private Checkbox gpsCheckbox;
	@Wire
	private Checkbox keylessCheckbox;
	@Wire
	private Selectbox typesSelectbox;
	@Wire
	private Combobox countriesCombobox;
	@Wire
	private Listbox salesmenListbox;

	public EditController() {
		// initialize data
		car = carService.findAll().get(0);

		// set default selection
		((ListModelList<String>)typesModel).addToSelection(car.getType());
		((ListModelList<String>)countriesModel).addToSelection(car.getCountry());
		((ListModelList<String>)salesmenModel).setMultiple(true);
		for(String salesman : car.getSalesmen())
			((ListModelList<String>)salesmenModel).addToSelection(salesman);
	}

	public Car getCar() {
		return car;
	}

	public ListModel<String> getTypesModel() {
		return typesModel;
	}

	public ListModel<String> getCountriesModel() {
		return countriesModel;
	}

	public ListModel<String> getSalesmenModel() {
		return salesmenModel;
	}

	@Listen("onCheck = #transmissionRadiogroup")
	public void changeTransmission() {
		Radio selectedItem = transmissionRadiogroup.getSelectedItem();
		car.setAutoTransmission("automaticRadio".equals(selectedItem.getId()));

		showNotify("Changed to: " + selectedItem.getLabel(), transmissionRadiogroup.getParent());
	}

	@Listen("onCheck = #accessories > checkbox")
	public void changeAccessory(CheckEvent event) {
		Accessories accessories = car.getAccessories();
		accessories.setAbs(absCheckbox.isChecked());
		accessories.setAirbag(airbagCheckbox.isChecked());
		accessories.setGps(gpsCheckbox.isChecked());
		accessories.setKeyless(keylessCheckbox.isChecked());

		showNotify("Changed to: " + accessories, absCheckbox.getParent());
	}

	@Listen("onSelect = #typesSelectbox")
	public void changeType() {
		Set<String> types = ((ListModelList<String>)typesModel).getSelection();
		String type = types.iterator().next();
		car.setType(type);
		showNotify("Changed to: " + type, typesSelectbox);
	}

	@Listen("onChange = #countriesCombobox")
	public void changeCountry() {
		String country = countriesCombobox.getValue();

		if(((ListModelList<String>)countriesModel).contains(country)) {
			car.setCountry(country);
			showNotify("Changed to: " + country, countriesCombobox);
		} else {
			showNotify("Unknow country : " + country, countriesCombobox);
		}
	}

	@Listen("onSelect = #salesmenListbox")
	public void changeSalesmen() {
		Set<String> salesmen = ((ListModelList<String>)salesmenModel).getSelection();
		car.setSalesmen(salesmen);

		showNotify("Changed to: " + salesmen, salesmenListbox);
	}

	@Listen("onClick = #submitButton")
	public void submit() {
		carService.store(car);

		showNotify("Saved", win);
	}

	private void showNotify(String msg, Component ref) {
		Clients.showNotification(msg, "info", ref, "end_center", 2000);
	}
}
