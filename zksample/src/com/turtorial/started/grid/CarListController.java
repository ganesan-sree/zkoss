package com.turtorial.started.grid;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;


public class CarListController extends SelectorComposer<Component> {
	private static final long serialVersionUID = 1L;

	private ListModel<Car> carsModel;

	public CarListController() {
		CarService carService = new CarServiceImpl();
		carsModel = new ListModelList<Car>(carService.findAll());
	}

	public ListModel<Car> getCarsModel() {
		return carsModel;
	}
}
