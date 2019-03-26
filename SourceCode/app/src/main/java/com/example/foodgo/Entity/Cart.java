package com.example.foodgo.Entity;

public class Cart {
    public int foodid;
    public String foodname;
    public float pricename;
    public int foodimage;
    public int foodnumber;

    public Cart() {
    }

    public Cart(int foodid, String foodname, float pricename, int foodimage, int foodnumber) {
        this.foodid = foodid;
        this.foodname = foodname;
        this.pricename = pricename;
        this.foodimage = foodimage;
        this.foodnumber = foodnumber;
    }

    public int getFoodid() {
        return foodid;
    }

    public void setFoodid(int foodid) {
        this.foodid = foodid;
    }

    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public float getPricename() {
        return pricename;
    }

    public void setPricename(float pricename) {
        this.pricename = pricename;
    }

    public int getFoodimage() {
        return foodimage;
    }

    public void setFoodimage(int foodimage) {
        this.foodimage = foodimage;
    }

    public int getFoodnumber() {
        return foodnumber;
    }

    public void setFoodnumber(int foodnumber) {
        this.foodnumber = foodnumber;
    }

}
