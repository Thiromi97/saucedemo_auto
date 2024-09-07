package com.saucedemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ProductsPage {
    final WebDriver webDriver;
    WebDriverWait wait ;

    @FindBy(css="span.title")
    WebElement txtProductPageTitle;

    @FindBy(className = "shopping_cart_link")
    WebElement btnShoppingCart;

    @FindBy(id = "react-burger-menu-btn")
    WebElement btnMenu;

    @FindBy(id = "logout_sidebar_link")
    WebElement btnLogout;

    @FindBy(id = "reset_sidebar_link")
    WebElement btnResetAppState;

    @FindBy(id = "about_sidebar_link")
    WebElement btnAbout;

    @FindBy(id = "inventory_sidebar_link")
    WebElement btnAllItems;

    @FindBy(className = "shopping_cart_link")
    WebElement btnCart;

    @FindBy(className = "product_sort_container")
    WebElement sortDropdown;

    @FindBy(className = "shopping_cart_badge")
    WebElement shoppingCartBadge;
    
    @FindBy(css = ".inventory_item img")
    List <WebElement> productImages;
    
    @FindBy(css = ".btn_inventory")
    List <WebElement> addToCartButtons;
    
    @FindBy(css = ".btn_secondary")
    List <WebElement> removeButtons;

    @FindBy(css = ".inventory_item_name")
    List <WebElement> productNames;

    @FindBy(className = "inventory_item_price")
    List <WebElement> productPrices;

    @FindBy(css = ".inventory_item_desc")
    List <WebElement> productsDescriptions;

    public ProductsPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver,this);
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
    }

    public ProductsPage clickMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(btnMenu)).click();
        return this;
    }

    public void clickLogout() {
        wait.until(ExpectedConditions.elementToBeClickable(btnLogout)).click();
    }

    public void selectResetAppState() {
        wait.until(ExpectedConditions.elementToBeClickable(btnResetAppState)).click();
    }

    public ProductsPage addProductToCart() {
        for (WebElement button:addToCartButtons){
            button.click();
        }
        return this;
    }

    public Integer getCartItemCount() {
        clickCart();
        CartPage cartPage = new CartPage(webDriver);
        return cartPage.getCartItemSize();
    }

    public void clickCart() {
        btnCart.click();
    }

    public void clickAbout() {
        wait.until(ExpectedConditions.elementToBeClickable(btnAbout)).click();
    }

    public void clickAllItem() {
        wait.until(ExpectedConditions.elementToBeClickable(btnAllItems)).click();
    }


    public void addSpecificProductToCart() {
        WebElement firstProduct = addToCartButtons.getFirst();
        firstProduct.click();
    }

    public String getCartBadgeCount() {
        return shoppingCartBadge.getText();
    }

    public void removeOneProduct() {
        removeButtons.getFirst().click();
    }

    public void removeProducts() {
        for (WebElement button:removeButtons){
            button.click();
        }
    }

    public void clickNameAToZ() {
        selectOption("Name (A to Z)");
    }

    private void selectOption(String text) {
        Select select = new Select(sortDropdown);
        select.selectByVisibleText(text);
    }


    public List<String> getSortedNameList() {
        List<String> sortedList = new ArrayList<>();
        for (WebElement item : productNames) {
            String itemName = item.getText();
            sortedList.add(itemName);
        }
        return sortedList;
    }

    public void clickNameZToA() {
        selectOption("Name (Z to A)");
    }

    public void clickPriceLowToHigh() {
        selectOption("Price (low to high)");
    }


    public List<Double> getSortedPriceList() {
        List<Double> sortedList = new ArrayList<>();
        for (WebElement itemPrice : productPrices) {
            String priceText = itemPrice.getText().replaceAll("[^0-9.]", ""); // Remove all non-numeric characters except for the decimal point
            Double numProductPrice = Double.valueOf(priceText);
            sortedList.add(numProductPrice);
        }
        return sortedList;
    }


    public WebElement getPageTitle() {
        return txtProductPageTitle;
    }

    public List<WebElement> getAddToCartButtonsList() {
        return addToCartButtons;
    }

    public List<WebElement> getRemoveButtonsList() {
        return removeButtons;
    }

    public List<WebElement> getProductNamesList() {
        return productNames;
    }

    public List<WebElement> getProductImagesList() {
        return productImages;
    }

    public WebElement getShoppingCartButton() {
        return btnShoppingCart;
    }

    public List<WebElement> getProductDescriptionList() {
        return productsDescriptions;
    }

    public List<WebElement> getProductPricesList() {
        return productPrices;
    }
}
