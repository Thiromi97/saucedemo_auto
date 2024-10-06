package com.saucedemo.pages;

import com.saucedemo.CartProduct;
import com.saucedemo.Product;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CartPage {
    final WebDriver webDriver;
    WebDriverWait wait ;

    @FindBy(className = "checkout_button")
    WebElement btnCheckout;

    @FindBy(className = "inventory_item_name")
    List <WebElement> cartItemNames;

    @FindBy(className = "inventory_item_desc")
    List <WebElement> cartItemDescriptions;

    @FindBy(className = "inventory_item_price")
    List<WebElement> cartItemPrices;

    @FindBy(className = "shopping_cart_badge")
    WebElement shoppingCartBadge;

    @FindBy(className = "cart_button")
    List <WebElement> removeButtons;

    @FindBy(className = "title")
    WebElement txtPageTitle;

    @FindBy(id="continue-shopping")
    WebElement btnContinueShoppingBtn;

    @FindBy(css = ".cart_item")
    List<WebElement> items;


    public CartPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver,this);
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
    }

    public List<CartProduct> extractProducts() {
        List<WebElement> cartItems = items;
        int productsSize = cartItems.size();
        List<CartProduct> products = new ArrayList<>();

        for (int i = 0; i < productsSize; i++) {
            WebElement proName = cartItemNames.get(i);
            WebElement proDesc = cartItemDescriptions.get(i);
            WebElement proPrice = cartItemPrices.get(i);
            String name = proName.getText();
            String description = proDesc.getText();
            String price = proPrice.getText();
            CartProduct product = new CartProduct(name, description, price);
            products.add(product);
        }

        return products;
    }

    public void clickCheckOut() {
        btnCheckout.click();
    }

    public void removeProduct() {
        WebElement firstRemoveButton = removeButtons.getFirst();
        firstRemoveButton.click();
    }

    public String getCartCount() {
        return shoppingCartBadge.getText();
    }

    public Integer getCartItemSize() {
        return cartItemNames.size();
    }

    public HashMap<String,String> getCartItemNames() {
        return getCartDetailHashMap(cartItemNames, "CartItemName");
    }

    public HashMap<String,String> getCartItemDescriptions(){
        return getCartDetailHashMap(cartItemDescriptions, "CartItemDescription");
    }

    public HashMap<String,String> getCartItemPrices(){
        return getCartDetailHashMap(cartItemPrices, "CartItemPrice");
    }

    private HashMap<String, String> getCartDetailHashMap(List<WebElement> fieldsValues, String fieldValue) {
        HashMap<String, String> cartNamesMap = new HashMap<>();
        for (int i = 0; i < fieldsValues.size(); i++) {
            WebElement cartItemName = fieldsValues.get(i);
            String txtCartItemName = cartItemName.getText();
            cartNamesMap.put(fieldValue + (i + 1), txtCartItemName);
        }
        return cartNamesMap;
    }

    public void clickContinueShoppingButton() {
        btnContinueShoppingBtn.click();
    }

    public WebElement getPageTitle() {
        return txtPageTitle;
    }
}
