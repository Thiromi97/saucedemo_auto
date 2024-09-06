package com.saucedemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CartPage {
    final WebDriver webDriver;
    WebDriverWait wait ;

    @FindBy(className = "checkout_button")
    WebElement btnCheckout;

//    @FindBy(className = "cart_item")
//    List <WebElement> cartItems;

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

    public CartPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver,this);
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
    }

    public void clickCheckOut() {
        btnCheckout.click();
    }

//    public List<WebElement> getCartItemList() {
//        return cartItem;
//    }

    public void removeProduct() {
        WebElement firstRemoveButton = removeButtons.getFirst();
        firstRemoveButton.click();
    }

    public String getCartCount() {
        return shoppingCartBadge.getText();
    }

    public void verifyCartItemsDeductByOne() {
        verifyCartItemCount(5);
    }

    public void checkCartIsEmpty() {
        verifyCartItemCount(0);
    }

    private void verifyCartItemCount(int expected) {
        Integer cartItemCount = getCartItemSize();
        assertThat(cartItemCount).isEqualTo(expected);
    }

    public Integer getCartItemSize() {
        return cartItemNames.size();
    }

    public void verifyNavigationToCartPage() {
        assertThat(txtPageTitle.getText()).isEqualTo("Your Cart");
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

    public void verifyCartBadgeUpdated() {
        String cartBadgeCount = getCartCount();
        assertThat(cartBadgeCount).isEqualTo("5");
    }

    public void clickContinueShoppingButton() {
        btnContinueShoppingBtn.click();
    }

}
