package com.sale.shop.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= Product.TABLE_NAME)
public class Product {
    public static final String TABLE_NAME = "products";

    @Id
    @GeneratedValue
    private long id;

    private String name;
    private String brand;
    private String madein;
    private Float price;

    public Product(String name, String brand, String madein, Float price) {
        this.name = name;
        this.brand = brand;
        this.madein = madein;
        this.price = price;
    }

    public Product() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getMadein() {
        return madein;
    }

    public void setMadein(String madein) {
        this.madein = madein;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
