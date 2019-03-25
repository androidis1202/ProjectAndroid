package com.example.foodgo.Entity;

import android.media.Image;
import android.widget.ImageView;

import java.io.Serializable;

public class Drink implements Serializable {
    private String name;
    private Integer id;
    private float price;
    private int image;
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
        this.image = image;
        this.description = description;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }


    public Drink() {
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
