package com.task1_screenscraper.products;

public class Product {
    // product separated to more easily mock/have test doubles of this class
    protected int alertType;
    protected String heading;
    protected String description;
    protected String url;
    protected String imageUrl;

    protected int priceInCents;

    // constructor injection
    public Product(int alertType, String heading, String description, String url, String imageUrl, int priceInCents) {
        this.alertType = alertType;
        this.heading = heading;
        this.description = description;
        this.url = url;
        this.imageUrl = imageUrl;
        this.priceInCents = priceInCents;
    }

//    // default constructor
//    public Product() {
//        this.alertType = -1;
//        this.heading = null;
//        this.description = null;
//        this.url = null;
//        this.imageUrl = null;
//        this.priceInCents = -1;
//    }

    public int getAlertType() {
        return alertType;
    }

    public String getHeading() {
        return heading;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getPriceInCents() {
        return priceInCents;
    }
}
