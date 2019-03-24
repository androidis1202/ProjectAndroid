package com.example.foodgo.Entity;

import android.media.Image;

import java.io.Serializable;

public class Drink implements Serializable {
    private String name;
    private Integer id;
    private float price;
    private int Image;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Drink(String name, Integer id, float price, int image, String description) {
        this.name = name;
        this.id = id;
        this.price = price;
        Image = image;
        this.description = description;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }


    public Drink(){
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
