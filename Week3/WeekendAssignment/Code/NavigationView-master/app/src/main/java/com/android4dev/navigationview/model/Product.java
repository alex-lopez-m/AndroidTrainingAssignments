package com.android4dev.navigationview.model;

/**
 * Created by Android1 on 8/10/2015.
 */
public class Product {
    int iconId;
    public String basePrice;
    public String brand;
    public String currentPrice;
    public boolean hasMoreColours;
    public boolean isInSet;
    public String previousPrice;
    public String productId;
    public String productImageUrl;
    public String RRP;
    public String title;

    public Product(String productImageUrl, String basePrice){
        this.productImageUrl = productImageUrl;
        this.basePrice = basePrice;
    }

}
