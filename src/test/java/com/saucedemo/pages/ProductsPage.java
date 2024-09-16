package com.saucedemo.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    private static final Logger logger = LogManager.getLogger(ProductsPage.class);

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
        logger.info("Click Menu");
        return this;
    }

    public void clickLogout() {
        logger.info("Click Logout");
        wait.until(ExpectedConditions.elementToBeClickable(btnLogout)).click();
    }

    public void selectResetAppState() {
        logger.info("Click ResetAppState");
        wait.until(ExpectedConditions.elementToBeClickable(btnResetAppState)).click();
    }

    public ProductsPage addProductToCart() {
        for (WebElement button:addToCartButtons){
            logger.info("Click AddToCart");
            button.click();
        }
        return this;
    }

    public Integer getCartItemCount() {
        clickCart();
        CartPage cartPage = new CartPage(webDriver);
        logger.info("Get cart item count");
        return cartPage.getCartItemSize();
    }

    public void clickCart() {
        logger.info("Click cart");
        btnCart.click();
    }

    public void clickAbout() {
        logger.info("Click About");
        wait.until(ExpectedConditions.elementToBeClickable(btnAbout)).click();
    }

    public void clickAllItem() {
        logger.info("Click All Item");
        wait.until(ExpectedConditions.elementToBeClickable(btnAllItems)).click();
    }


    public void addSpecificProductToCart() {
        WebElement firstProduct = addToCartButtons.getFirst();
        logger.info("Add first product");
        firstProduct.click();
    }

    public String getCartBadgeCount() {
        logger.info("Get cart badge count");
        return shoppingCartBadge.getText();
    }

    public void removeOneProduct() {
        logger.info("Remove first product");
        removeButtons.getFirst().click();
    }

    public void removeProducts() {
        for (WebElement button:removeButtons){
            logger.info("Remove product");
            button.click();
        }
    }

    public void clickNameAToZ() {
        logger.info("Click Name(A to Z)");
        selectOption("Name (A to Z)");
    }

    private void selectOption(String text) {
        Select select = new Select(sortDropdown);
        logger.info("Select option");
        select.selectByVisibleText(text);
    }


    public List<String> getSortedNameList() {
        List<String> sortedList = new ArrayList<>();
        for (WebElement item : productNames) {
            String itemName = item.getText();
            sortedList.add(itemName);
        }
        logger.info(sortedList);
        return sortedList;
    }

    public void clickNameZToA() {
        logger.info("Click Name(Z to A)");
        selectOption("Name (Z to A)");
    }

    public void clickPriceLowToHigh() {
        logger.info("Click Price (low to high)");
        selectOption("Price (low to high)");
    }


    public List<Double> getSortedPriceList() {
        List<Double> sortedList = new ArrayList<>();
        for (WebElement itemPrice : productPrices) {
            String priceText = itemPrice.getText().replaceAll("[^0-9.]", ""); // Remove all non-numeric characters except for the decimal point
            Double numProductPrice = Double.valueOf(priceText);
            sortedList.add(numProductPrice);
        }
        logger.info(sortedList);
        return sortedList;
    }


    public WebElement getPageTitle() {
        logger.info("Get page title");
        return txtProductPageTitle;
    }

    public List<WebElement> getAddToCartButtonsList() {
        logger.info("Get AddToCart Button list");
        return addToCartButtons;
    }

    public List<WebElement> getRemoveButtonsList() {
        logger.info("Get remove button list");
        return removeButtons;
    }

    public List<WebElement> getProductNamesList() {
        logger.info("Get product name list");
        return productNames;
    }

    public List<WebElement> getProductImagesList() {
        logger.info("Get product images list ");
        return productImages;
    }

    public WebElement getShoppingCartButton() {
        logger.info("Get shopping cart buttons list");
        return btnShoppingCart;
    }

    public List<WebElement> getProductDescriptionList() {
        logger.info("Get production description list");
        return productsDescriptions;
    }

    public List<WebElement> getProductPricesList() {
        logger.info("Get product prices list");
        return productPrices;
    }
}
