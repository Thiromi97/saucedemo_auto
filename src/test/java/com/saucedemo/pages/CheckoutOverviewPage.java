package com.saucedemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

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

    public void verifyNavigationToOverviewPageSuccessfully() {
        assertThat(txtOverviewTitle.getText()).isEqualTo("Checkout: Overview");
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

    public void checkItemTotalIsSumOfProductPrices() {
        double total = 0.00;
        for(WebElement price:getProductPrices()){
            String priceText = price.getText().replaceAll("[^0-9.]", "");
            double numProductPrice = Double.parseDouble(priceText);
            total = total +numProductPrice ;
        }
        String itemTotal = txtItemTotal.getText().replaceAll("[^0-9.]","");
        double actualItemTotal = Double.parseDouble(itemTotal);
        assertThat(actualItemTotal).isEqualTo(total);
    }

    public void checkTotalIsSumOfItemTotalAndTax() {
        String itemTotal = txtItemTotal.getText().replaceAll("[^0-9.]","");
        double numItemTotal = Double.parseDouble(itemTotal);
        String itemTax = txtTax.getText().replaceAll("[^0-9.]","");
        double numItemTax = Double.parseDouble(itemTax);
        double expectedTotal = numItemTotal + numItemTax;
        String total = txtTotal.getText().replaceAll("[^0-9.]","");
        double actualTotal = Double.parseDouble(total);
        assertThat(actualTotal).isEqualTo(expectedTotal);
    }

    public void clickCancel() {
        btnCancel.click();
    }
}
