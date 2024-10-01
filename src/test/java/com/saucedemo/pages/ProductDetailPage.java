package com.saucedemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductDetailPage {

   final WebDriver webDriver;

   @FindBy(className = "inventory_details_name")
    WebElement txtProductTitle;

   @FindBy(className = "inventory_details_img")
   WebElement imgProduct;

   @FindBy(className = "inventory_details_desc")
   WebElement txtProductDescription;

   @FindBy(className = "inventory_details_price")
   WebElement txtProductPrice;

   @FindBy(id = "add-to-cart")
   WebElement btnAddToCart;

    public ProductDetailPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver,this);
    }

    public String getProductName() {
        return txtProductTitle.getText();
    }

    public WebElement getProductTitle(){
        return txtProductTitle;
    }
    public String getProductDescription() {
        return txtProductDescription.getText();
    }

    public String getProductPrice() {
        return txtProductPrice.getText();
    }

    public WebElement getProductImage() {
        return imgProduct;
    }

    public WebElement getBtnAddToCart() {
        return btnAddToCart;
    }

    public String getProductImageSrc(){
        return imgProduct.getAttribute("src");
    }
}
