/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;

/**
 *
 * @author siddharth
 */
public class Product implements Serializable{

    private String productCode;
    private String productName;
    private String catalogCategory;
    private String description;
    private float price;
    private String getImageURL;
    
    public Product() {
        productCode = "";
        productName = "";
        catalogCategory = "";
        description = "";
        price =0;
        getImageURL = "";
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setCatalogCategory(String catalogCategory) {
        this.catalogCategory = catalogCategory;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setGetImageURL(String getImageURL) {
        this.getImageURL = getImageURL;
    }

    public String getProductCode() {
        return productCode;
    }

    public String getProductName() {
        return productName;
    }

    public String getCatalogCategory() {
        return catalogCategory;
    }

    public String getDescription() {
        return description;
    }

    public float getPrice() {
        return price;
    }

    public String getGetImageURL() {
        return getImageURL;
    }
}
