package com.example.foodgo.Entity;

public class Cart {
    public int foodid;
    public String foodname;
    public long pricename;
    public String foodimage;
    public int foodnumber;

    public Cart() {
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

    public long getPricename() {
        return pricename;
    }

    public void setPricename(long pricename) {
        this.pricename = pricename;
    }

    public String getFoodimage() {
        return foodimage;
    }

    public void setFoodimage(String foodimage) {
        this.foodimage = foodimage;
    }

    public int getFoodnumber() {
        return foodnumber;
    }

    public void setFoodnumber(int foodnumber) {
        this.foodnumber = foodnumber;
    }
    
}
