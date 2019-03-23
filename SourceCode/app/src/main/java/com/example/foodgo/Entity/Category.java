package com.example.foodgo.Entity;

import java.io.Serializable;

public class Category implements Serializable {

    private int id;
    private String name;
    private int image;

    public Category() {
    }

    public Category(int id, String name, int image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }
    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
