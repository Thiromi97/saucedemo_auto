package com.saucedemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CheckoutOverviewPage {
   final WebDriver webDriver;

   @FindBy(id = "finish")
   WebElement btnFinish;

   @FindBy(id ="cancel")
   WebElement btnCancel;

    @FindBy(css = ".title")
    WebElement txtOverviewTitle;

    @FindBy(css = ".inventory_item_name")
    List <WebElement> productNames;

    @FindBy(css = ".inventory_item_desc")
    List<WebElement> productDescriptions;

    @FindBy(css = ".inventory_item_price")
    List<WebElement> productPrices;

    @FindBy(css =".summary_subtotal_label")
    WebElement txtItemTotal;

    @FindBy(css = ".summary_tax_label")
    WebElement txtTax;

    @FindBy(css = ".summary_total_label")
    WebElement txtTotal;

    public CheckoutOverviewPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver,this);
    }

    public void clickFinish() {
        btnFinish.click();
    }

    public List<WebElement> getProductNames() {
        return productNames;
    }

    public List<WebElement> getProductDescriptions() {
        return productDescriptions;
    }

    public List<WebElement> getProductPrices() {
        return productPrices;
    }

    public int getNoOfItems() {
        return productNames.size();
    }


    public void clickCancel() {
        btnCancel.click();
    }

    public WebElement getPageTitle() {
        return txtOverviewTitle;
    }

    public WebElement getItemTotal() {
        return txtItemTotal;
    }

    public WebElement getTax() {
        return txtTax;
    }

    public WebElement getTotal() {
        return txtTotal;
    }
}
