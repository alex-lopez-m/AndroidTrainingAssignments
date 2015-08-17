package com.android4dev.navigationview.model;

/**
 * Created by Android1 on 8/16/2015.
 */
public class Marker {

    public String id;
    public String name;
    public String address;
    public double latitude;
    public double longitude;

    public Marker(String id, String name, String address, double latitude, double longitude){
        this.id = id;
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
