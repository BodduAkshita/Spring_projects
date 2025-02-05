package com.task.itemslist.item;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Items {
    @Id
    private int id;
    
    @Column(name="name")
    private String name;
    
    @Column(name="uof")
    private String unitOfMeasurement;
    
    @Column(name="noi")
    private int noOfItems;

    public Items(int id, String name, String unitOfMeasurement, int noOfItems) {
        this.id = id;
        this.name = name;
        this.unitOfMeasurement = unitOfMeasurement;
        this.noOfItems = noOfItems;
    }
    

    public Items() {
	}


	public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUnitOfMeasurement() {
        return unitOfMeasurement;
    }

    public int getNoOfItems() {
        return noOfItems;
    }

    @Override
    public String toString() {
        return "Items [id=" + id + ", name=" + name + ", unitOfMeasurement=" + unitOfMeasurement + ", noOfItems=" + noOfItems + "]";
    }
}
