package com.saucedemo;

public class Product {
    private final String name;
    private final String description;
    private final String price;
    private final String imageSrc;

    public Product(String name, String description, String price, String imageSrc) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageSrc = imageSrc;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    public String getImageSrc(){
        return imageSrc;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price='" + price + '\'' +
                ", image='" + imageSrc + '\'' +
                '}';
    }


}
