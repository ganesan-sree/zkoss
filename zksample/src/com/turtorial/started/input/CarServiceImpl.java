package com.turtorial.started.input;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.turtorial.started.mvc.Car;

public class CarServiceImpl {

	private List<Car> cars = new ArrayList<Car>(20);
	private Map<String, List<Car>> queryResults;
	private Category rootCategory;

	public CarServiceImpl() {

		Random r = new Random(0L); // random but same data
		String[][] raw = {{"China", "Geely", "Panda", "Sedan"}, {"France", "Peugeot", "RCZ", "Sport"},
				{"France", "Renault", "Megane", "Sedan"}, {"Germany", "Audi", "TT", "Sport"},
				{"Germany", "BMW", "X3", "SUV"}, {"Germany", "Mercedes-Benz", "Sprinter", "Van"},
				{"Germany", "Volkswagen", "Transporter", "Van"}, {"Germany", "Porsche", "Cayenne", "SUV"},
				{"Italy", "Ferrari", "F430", "Supercar"}, {"Italy", "Lamborghini", "Gallardo", "Supercar"},
				{"Japan", "Toyota", "Camry", "Sedan"}, {"Japan", "Mitsubishi", "Pajero", "SUV"},
				{"Japan", "Nissan", "GT-R", "Sport"}, {"Japan", "Subaru", "Legacy", "MPV"},
				{"Korea", "Hyundai", "Tucson", "SUV"}, {"Korea", "Kia", "Rio", "Sedan"},
				{"Sweden", "Volvo", "S40", "Sedan"}, {"Taiwan", "LUXGEN", "Luxgen7", "MPV"},
				{"United States", "Ford", "Focus", "Sedan"},
				{"United Kingdom", "Land Rover", "Discovery", "SUV"}};
		int[] disp = new int[]{1600, 1800, 2000, 2400, 3000, 3200, 3500}; // engine displacement
		double[] cost = new double[]{19999.9, 24999.9, 29999.9, 34999.9, 39999.9}; // cost

		// create in-memory data
		for(int i = 0; i < 20; ++i) {
			Car car = new Car();			
			car.setMake(raw[i][1]);
			car.setModel(raw[i][2]);		
			cars.add(car);
		}
		Collections.shuffle(cars, r);

	}



	public void store(Car car) {
		// process data
		// ...
	}

	public void store(InventoryItem inventoryItem) {
		System.out.println(inventoryItem);
	}

	public void order(List<OrderItem> orderItems) {

	}

	public Category getCarCategoriesRoot() {
		return rootCategory;
	}



	
}
