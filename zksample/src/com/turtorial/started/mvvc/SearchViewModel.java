package com.turtorial.started.mvvc;

import java.util.List;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import com.turtorial.started.mvc.Car;
import com.turtorial.started.mvc.CarService;
import com.turtorial.started.mvc.CarServiceImpl;


public class SearchViewModel {
	
	private String keyword;
	private List<Car> carList;
	private Car selectedCar;
	
	private CarService carService = new CarServiceImpl();
	
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getKeyword() {
		return keyword;
	}

	public List<Car> getCarList(){
		return carList;
	}
	
		
	public void setSelectedCar(Car selectedCar) {
		this.selectedCar = selectedCar;
	}
	public Car getSelectedCar() {
		return selectedCar;
	}

	
	@Command
	@NotifyChange("carList")
	public void search(){
		carList = carService.search(keyword);
	}
}
