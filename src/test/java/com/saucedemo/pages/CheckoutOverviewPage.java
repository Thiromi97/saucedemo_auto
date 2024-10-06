package com.saucedemo.pages;

import com.saucedemo.OverviewProduct;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
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

    @FindBy(className = "cart_item_label")
    List<WebElement> items;

    public CheckoutOverviewPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver,this);
    }

    public List<OverviewProduct> extractProducts() {
        List<WebElement> overviewItems = items;
        int productsSize = overviewItems.size();
        List<OverviewProduct> products = new ArrayList<>();

        for (int i = 0; i < productsSize; i++) {
            WebElement proName = productNames.get(i);
            WebElement proDesc = productDescriptions.get(i);
            WebElement proPrice = productPrices.get(i);
            String name = proName.getText();
            String description = proDesc.getText();
            String price = proPrice.getText();
            OverviewProduct product = new OverviewProduct(name, description, price);
            products.add(product);
        }

        return products;
    }

    public void clickFinish() {
        btnFinish.click();
    }

    public List<WebElement> getProductPrices() {
        return productPrices;
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
