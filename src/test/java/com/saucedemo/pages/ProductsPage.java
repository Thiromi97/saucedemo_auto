package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

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

    public void verifySuccessNavigationToProductPage() {
        WebElement productTitle = wait.until(ExpectedConditions.visibilityOf(txtProductPageTitle));
        assertThat(productTitle.getText()).isEqualTo("Products");
    }

    public void verifyProductImagesDuplication() {
        String firstImageSrc = productImages.getFirst().getAttribute("src");
        for(WebElement productImage:productImages){
            String imageSrc = productImage.getAttribute("src");
            assertThat(imageSrc).isEqualTo(firstImageSrc);
        }

    }
    
    public void verifyAddToCartBtnMalfunction() {
        verifyButtonFunctionality(addToCartButtons);
    }

    public void verifyRemoveBtnMalfunction() {
        verifyButtonFunctionality(removeButtons);
    }

    private void verifyButtonFunctionality(List<WebElement> buttonList) {
        boolean buttonsFunctioning = true;
        for (int i = 0; i < buttonList.size(); i++) {
            String btnInitialText = getElementText(buttonList, i);
            WebElement button;
            // Re-find the button to ensure it hasn't changed in the DOM(it take the list again and fetch the correct btn)
            button = buttonList.get(i);
            if (button.getText().equals(btnInitialText)) {
                buttonsFunctioning = false;
                break;
            }
        }
        assertThat(buttonsFunctioning).isFalse();
    }

    private static String getElementText(List<WebElement> buttonList, int i) {
        WebElement button = buttonList.get(i);
        String btnInitialText = button.getText();
        button.click();
        return btnInitialText;
    }

    public void verifyIncorrectProductDetailPageNavigation() {
        for (int i = 0; i < productNames.size(); i++) {
            String originalTitle = getElementText(productNames, i);
            ProductDetailPage productDetailPage =new ProductDetailPage(webDriver);
            String detailPageTitle = productDetailPage.getProductName();
            assertThat(detailPageTitle).isEqualTo(originalTitle);
            webDriver.navigate().back();
        }
    }

    public void verifyProductImagesMismatching() {
        for (int i = 0; i < productImages.size(); i++) {
            //relocate image
            productImages = webDriver.findElements(By.cssSelector(".inventory_item img"));
            WebElement productImage = productImages.get(i);
            String productImageSrc = productImage.getAttribute("src");
            productImage.click();
            ProductDetailPage productDetailPage =new ProductDetailPage(webDriver);
            String productDetailImgSrc = productDetailPage.getProductImageSrc();
            assertThat(productImageSrc).isEqualTo(productDetailImgSrc);
            webDriver.navigate().back();
        }
    }

    public void verifySuccessCompletionOfCheckout() {
        btnShoppingCart.click();
        CartPage cartPage = new CartPage(webDriver);
        cartPage.clickCheckOut();
        CheckoutDetailPage checkoutDetailPage = new CheckoutDetailPage(webDriver);
        checkoutDetailPage.fillFirstName().fillLastName().fillPostalCode().clickContinue();
        CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(webDriver);
        checkoutOverviewPage.clickFinish();
        CheckoutCompletePage completePage = new CheckoutCompletePage(webDriver);
        String completePageTitle = completePage.getPageTitle();
        assertThat(completePageTitle).isEqualTo("Checkout: Complete!");
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

    public void verifyCartIsEmpty() {
        assertThat(getCartItemCount()).isEqualTo(0);
        }

    public void checkProductIsAddedToTheCart() {
        assertThat(getCartItemCount()).isEqualTo(1);
    }

    private Integer getCartItemCount() {
        clickCart();
        CartPage cartPage = new CartPage(webDriver);
        return cartPage.getCartItemSize();
//        webDriver.navigate().back();
    }

    public void clickCart() {
        btnCart.click();
    }

    public void verifyAllRemoveButtonsReset() {
        for(WebElement button:removeButtons){
            String buttonName = button.getText();
            assertThat(buttonName).isEqualTo("Add to cart");
        }
    }

    public void clickAbout() {
        wait.until(ExpectedConditions.elementToBeClickable(btnAbout)).click();
    }

    public void clickAllItem() {
        wait.until(ExpectedConditions.elementToBeClickable(btnAllItems)).click();
    }

    public void verifyDisplayOfAllItems() {
        assertThat(productNames.size()).isEqualTo(6);
    }

    public void checkAllAddToCartConvertRemoveButtons() {
        for(WebElement button:removeButtons){
            String buttonName = button.getText();
            assertThat(buttonName).isEqualTo("Remove");
        }
    }

    public ProductsPage addSpecificProductToCart() {
        WebElement firstProduct = addToCartButtons.getFirst();
        firstProduct.click();
        return this;
    }

    public void checkAddToCartConvertToRemove() {
        WebElement firstProduct = removeButtons.getFirst();
        assertThat(firstProduct.getText()).isEqualTo("Remove");
    }

    public void checkCartBadgeIsOne() {
        assertThat(getCartBadgeCount()).isEqualTo("1");
    }

    public void checkCartBadgeIsUpdated() {
        assertThat(getCartBadgeCount()).isEqualTo("6");
    }

    public void checkProductAddedToTheCart() {
        assertThat(getCartItemCount()).isEqualTo(1);
    }

    public void checkAllProductsAddedToTheCart() {
        assertThat(getCartItemCount()).isEqualTo(6);
    }
    public String getCartBadgeCount() {
        return shoppingCartBadge.getText();
    }

    public void removeOneProduct() {
        removeButtons.getFirst().click();
    }

    public void checkRemoveOneProductUpdateInCartBadge() {
        assertThat(getCartBadgeCount()).isEqualTo("5");
    }

    public ProductsPage removeProducts() {
        for (WebElement button:removeButtons){
            button.click();
        }
        return this;
    }

    public ProductsPage clickNameAToZ() {
        selectOption("Name (A to Z)");
        return this;
    }

    private void selectOption(String text) {
        Select select = new Select(sortDropdown);
        select.selectByVisibleText(text);
    }

    public void checkProductsSortedAToZ() {
        List<String> sortedResult = getSortedNameList();
        List<String> expectedNames = new ArrayList<>(sortedResult);
        Collections.sort(expectedNames);
        assertThat(sortedResult).isEqualTo(expectedNames);

    }

    public void checkProductsSortedZToA() {
        List<String> sortedResult = getSortedNameList();
        List<String> expectedNames = new ArrayList<>(sortedResult);
        expectedNames.sort(Collections.reverseOrder());
        assertThat(sortedResult).isEqualTo(expectedNames);
    }

    private List<String> getSortedNameList() {
        List<String> sortedList = new ArrayList<>();
        for (WebElement item : productNames) {
            String itemName = item.getText();
            sortedList.add(itemName);
        }
        return sortedList;
    }

    public ProductsPage clickNameZToA() {
        selectOption("Name (Z to A)");
        return this;
    }

    public ProductsPage clickPriceLowToHigh() {
        selectOption("Price (low to high)");
        return this;
    }

    public void checkProductsSortedLowToHighPrices() {
        List<Double> sortedList = getSortedPriceList();
        List<Double> expectedNames = new ArrayList<>(sortedList);
        Collections.sort(expectedNames);
        assertThat(sortedList).isEqualTo(expectedNames);
    }

    private List<Double> getSortedPriceList() {
        List<Double> sortedList = new ArrayList<>();
        for (WebElement itemPrice : productPrices) {
            String priceText = itemPrice.getText().replaceAll("[^0-9.]", ""); // Remove all non-numeric characters except for the decimal point
            Double numProductPrice = Double.valueOf(priceText);
            sortedList.add(numProductPrice);
        }
        return sortedList;
    }

    public void checkProductsSortedHighToLowPrices() {
        List<Double> sortedList = getSortedPriceList();
        List<Double> expectedNames = new ArrayList<>(sortedList);
        Collections.sort(expectedNames.reversed());
        assertThat(sortedList).isEqualTo(expectedNames);
    }

    public ProductsPage clickPriceHighToLow() {
        selectOption("Price (high to low)");
        return this;
    }

    public void checkProductTitleRedirectToProductDetailPage() {
        for (int i = 0; i < productNames.size(); i++) {
            WebElement productTitle = productNames.get(i);
            String txtProductTitle = productTitle.getText();
            productTitle.click();
            ProductDetailPage productDetailPage =new ProductDetailPage(webDriver);
            String txtProductDetailPageTitle = productDetailPage.getProductName();
            Boolean image = productDetailPage.getProductImage().isDisplayed();
            isAllProductDetailsAreDisplaying(productDetailPage);
            assertThat(txtProductTitle).isEqualTo(txtProductDetailPageTitle);
            assertThat(image).isTrue();
            webDriver.navigate().back();
        }
    }

    public void checkProductImageRedirectToProductDetailPage() {
        for (int i = 0; i < productImages.size(); i++) {
            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            WebElement productImage = productImages.get(i);
            String txtImageSrc = productImage.getAttribute("src");
            productImage.click();
            ProductDetailPage productDetailPage =new ProductDetailPage(webDriver);
            String productDetailPageImgSrc = productDetailPage.getProductImageSrc();
            Boolean title = productDetailPage.getProductTitle().isDisplayed();
            isAllProductDetailsAreDisplaying(productDetailPage);
            assertThat(txtImageSrc).isEqualTo(productDetailPageImgSrc);
            assertThat(title).isTrue();
            webDriver.navigate().back();
        }
    }

    private static void isAllProductDetailsAreDisplaying(ProductDetailPage productDetailPage) {
        Boolean description = productDetailPage.getProductDescription().isDisplayed();
        Boolean price = productDetailPage.getProductPrice().isDisplayed();
        Boolean addToCartButton = productDetailPage.getBtnAddToCart().isDisplayed();
        assertThat(description).isTrue();
        assertThat(price).isTrue();
        assertThat(addToCartButton).isTrue();
    }


    public void checkCartItemDetails() {
        HashMap<String, String> productNamesMap = new HashMap<>();
        HashMap<String, String> productDescriptionMap = new HashMap<>();
        HashMap<String, String> productPricesMap = new HashMap<>();
        putProductDetails(productNames, productNamesMap, "ProductName");
        putProductDetails(productsDescriptions, productDescriptionMap, "ProductDescription");
        putProductDetails(productPrices, productPricesMap, "ProductPrice");
        CartPage cartPage = new CartPage(webDriver);
        HashMap<String,String> cartItemNames = cartPage.getCartItemNames();
        HashMap<String,String> cartItemDescriptions = cartPage.getCartItemDescriptions();
        HashMap<String,String> cartItemPrices = cartPage.getCartItemPrices();
        for(int i=0;i<cartPage.getCartItemSize();i++){
            assertThat(cartItemNames.get("CartItemName"+(i+1))).isEqualTo(productNamesMap.get("ProductName"+(i+1)));
            assertThat(cartItemDescriptions.get("CartItemDescription"+(i+1))).isEqualTo(productDescriptionMap.get("ProductDescription"+(i+1)));
            assertThat(cartItemPrices.get("CartItemPrice"+(i+1))).isEqualTo(productPricesMap.get("ProductPrice"+(i+1)));
        }
    }

    private void putProductDetails(List<WebElement> productNames, HashMap<String, String> productNamesMap, String ProductName) {
        for (int i = 0; i < productNames.size(); i++) {
            WebElement productName = productNames.get(i);
            String txtProductName = productName.getText();
            productNamesMap.put(ProductName + (i + 1), txtProductName);
        }
    }
}
