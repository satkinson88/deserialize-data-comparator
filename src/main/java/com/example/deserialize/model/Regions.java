package com.example.deserialize.model;

public class Regions {

    private int region_id;
    private String region;
    private String country;

    public int getRegion_id() {
        return this.region_id;
    }

    public void setRegion_id(int region_id) {
        this.region_id = region_id;
    }

    public String getRegion(){
        return this.region;
    }

    public void setRegion(String region){
        this.region = region;
    }

    public String getCountry(){
        return this.country;
    }

    public void setCountry(String country){
        this.country = country;
    }

}
