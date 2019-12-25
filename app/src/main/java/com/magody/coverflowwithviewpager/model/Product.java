package com.magody.coverflowwithviewpager.model;


import java.io.Serializable;

public class Product implements  Serializable {

    private String name;
    private String urlImage;

    public Product(String name, String urlImage) {
        this.name = name;
        this.urlImage = urlImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }
}
