package com.saurinppatel.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Item {

	@Id
	private String name;
	private String type;
	private int currentQuantity;
	private double size;
	private UnitsOfMeasurements units;

	public Item() {

	}

	public Item(String name, String type) {
		this.name = name;
		this.type = type;
	}

	public Item(String name, String type, int currentQuantity, double size, UnitsOfMeasurements units) {
		this.name = name;
		this.type = type;
		this.currentQuantity = currentQuantity;
		this.size = size;
		this.units = units;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getCurrentQuantity() {
		return this.currentQuantity;
	}

	public void setCurrentQuantity(int currentQuantity) {
		this.currentQuantity = currentQuantity;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public UnitsOfMeasurements getUnits() {
		return units;
	}

	public void setUnits(UnitsOfMeasurements units) {
		this.units = units;
	}

}
