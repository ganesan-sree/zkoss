package com.turtorial.started.listbox;

import java.util.Set;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Window;

public class ListboxController extends SelectorComposer<Component> {

	private static final long serialVersionUID = 1L;

	private ListModel<Car> carsModel;

	@Wire
	private Window win;
	
	public ListboxController() {
		CarService carService = new CarServiceImpl();
		carsModel = new ListModelList<Car>(carService.findAll());
		((ListModelList<Car>)carsModel).setMultiple(true);
	}

	public ListModel<Car> getCarsModel() {
		return carsModel;
	}

	@Listen("onSelect = listbox")
	public void updateMessage() {
		Set<Car> selectedCars = ((ListModelList<Car>)carsModel).getSelection();
		int size = selectedCars.size();
		
		showNotify(size > 0 ? size + " cars selected: " + selectedCars : "no car selected", win);
	}
	
	private void showNotify(String msg,Component ref){
		Clients.showNotification(msg,"info",ref,"top_right",2000);
	}
}
