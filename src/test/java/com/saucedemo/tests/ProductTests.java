package com.saucedemo.tests;

import com.saucedemo.TestBase;
import com.saucedemo.pages.CartPage;
import com.saucedemo.pages.ProductDetailPage;
import com.saucedemo.pages.ProductsPage;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductTests extends TestBase {

    @Test
    public void verifyAddToCartFunctionalityForOneProduct(){
        loginAsStandardUser();
        verifyAddToCartButtonChangeToRemoveState();
        verifyCartBadgeUpdateToOne();
        verifyAddedProductDisplayInTheCart();
    }

    @Test
    public void verifyAddToCartFunctionalityForMultipleProducts(){
        loginAsStandardUser();
        verifyAllAddToCartButtonStateChangeToRemove();
        verifyCartBadgeReflectTheAddedProductCount();
        verifyAllAddedProductsDisplayInTheCart();
    }

    @Test
    public void verifyRemoveButtonFunctionalityForOneProduct(){
        loginAsStandardUser();
        verifyCartBadgeCountDeductedByOne();
        goToCartPage();
        verifyProductIsRemovedFromTheCart();
    }

    @Test
    public void verifyRemoveButtonFunctionalityForMultipleProducts(){
        loginAsStandardUser();
        verifyCartBadgeCountDeductedByRemovedCount();
        goToCartPage();
        verifyProductsSuccessfullyRemovedFromCart();
    }

    @Test
    public void verifySortProductsByNameAtoZ(){
        loginAsStandardUser();
        verifyProductAreReorderedAtoZ();
    }

    @Test
    public void verifySortProductsByNameZtoA(){
        loginAsStandardUser();
        verifyProductsAreReorderedZtoA();
    }

    @Test
    public void verifySortProductsByPriceLowToHigh(){
        loginAsStandardUser();
        verifyProductsReorderedLowToHigh();
    }

    @Test
    public void verifySortProductsByPriceHighToLow(){
        loginAsStandardUser();
        verifyProductsReorderedHighToLow();
    }

    @Test
    public void verifyViewProductDetailsByProductTitle(){
        loginAsStandardUser();
        verifyClickingProductTitleRedirectToCorrectProductDetailPage();
    }

    @Test
    public void verifyViewProductDetailsByProductImage(){
        loginAsStandardUser();
        verifyClickingProductImageRedirectToCorrectProductDetailPage();
    }

    @Test
    public void verifyShoppingCartButtonFunctionality(){
        loginAsStandardUser();
        goToCartPage();
        verifyCartPageLoadedSuccessfully();
    }

    private static void verifyClickingProductImageRedirectToCorrectProductDetailPage() {
        ProductsPage productsPage = new ProductsPage(webDriver);
        List<WebElement> productImages = productsPage.getProductImagesList();
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

    private static void verifyClickingProductTitleRedirectToCorrectProductDetailPage() {
        ProductsPage productsPage = new ProductsPage(webDriver);
        List<WebElement> productNames = productsPage.getProductNamesList();
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
    private static void isAllProductDetailsAreDisplaying(ProductDetailPage productDetailPage) {
        Boolean description = productDetailPage.getProductDescription().isDisplayed();
        Boolean price = productDetailPage.getProductPrice().isDisplayed();
        Boolean addToCartButton = productDetailPage.getBtnAddToCart().isDisplayed();
        assertThat(description).isTrue();
        assertThat(price).isTrue();
        assertThat(addToCartButton).isTrue();
    }

    private static void verifyProductsReorderedLowToHigh() {
        ProductsPage productsPage = new ProductsPage(webDriver);
        productsPage.clickPriceLowToHigh();
        List<Double> sortedList = productsPage.getSortedPriceList();
        List<Double> expectedNames = new ArrayList<>(sortedList);
        Collections.sort(expectedNames);
        assertThat(sortedList).isEqualTo(expectedNames);
    }


    private static void verifyProductsReorderedHighToLow() {
        ProductsPage productsPage = new ProductsPage(webDriver);
        List<Double> sortedList = productsPage.getSortedPriceList();
        List<Double> expectedNames = new ArrayList<>(sortedList);
        Collections.sort(expectedNames);
        assertThat(sortedList).isEqualTo(expectedNames);
    }


    private static void verifyProductsAreReorderedZtoA() {
        ProductsPage productsPage = new ProductsPage(webDriver);
        productsPage.clickNameZToA();
        List<String> sortedResult = productsPage.getSortedNameList();
        List<String> expectedNames = new ArrayList<>(sortedResult);
        expectedNames.sort(Collections.reverseOrder());
        assertThat(sortedResult).isEqualTo(expectedNames);
    }


    private static void verifyProductAreReorderedAtoZ() {
        ProductsPage productsPage = new ProductsPage(webDriver);
        productsPage.clickNameAToZ();
        List<String> sortedResult = productsPage.getSortedNameList();
        List<String> expectedNames = new ArrayList<>(sortedResult);
        Collections.sort(expectedNames);
        assertThat(sortedResult).isEqualTo(expectedNames);
    }

    private static void verifyCartBadgeCountDeductedByRemovedCount() {
        ProductsPage productsPage = new ProductsPage(webDriver);
        productsPage.addProductToCart().removeProducts();
        assertThat(productsPage.getCartItemCount()).isEqualTo(0);
    }


    private static void verifyProductsSuccessfullyRemovedFromCart() {
        CartPage cartPage=new CartPage(webDriver);
        Integer cartItemCount = cartPage.getCartItemSize();
        assertThat(cartItemCount).isEqualTo(0);
    }

    private static void verifyCartBadgeCountDeductedByOne() {
        ProductsPage productsPage=new ProductsPage(webDriver);
        productsPage.addProductToCart().removeOneProduct();
        assertThat(productsPage.getCartBadgeCount()).isEqualTo("5");
    }

    private static void verifyAllAddedProductsDisplayInTheCart() {
        ProductsPage productsPage=new ProductsPage(webDriver);
        assertThat(productsPage.getCartItemCount()).isEqualTo(6);
    }

    private static void verifyCartBadgeReflectTheAddedProductCount() {
        ProductsPage productsPage=new ProductsPage(webDriver);
        assertThat(productsPage.getCartBadgeCount()).isEqualTo("6");
    }

    private static void verifyAllAddToCartButtonStateChangeToRemove() {
        ProductsPage productsPage=new ProductsPage(webDriver);
        productsPage.addProductToCart();
        List<WebElement> removeButtons = productsPage.getRemoveButtonsList();
        for(WebElement button:removeButtons){
            String buttonName = button.getText();
            assertThat(buttonName).isEqualTo("Remove");
        }
    }

    private static void verifyAddedProductDisplayInTheCart() {
        ProductsPage productsPage=new ProductsPage(webDriver);
        assertThat(productsPage.getCartItemCount()).isEqualTo(1);
    }

    private static void verifyCartBadgeUpdateToOne() {
        ProductsPage productsPage=new ProductsPage(webDriver);
        assertThat(productsPage.getCartBadgeCount()).isEqualTo("1");
    }

    private static void verifyAddToCartButtonChangeToRemoveState() {
        ProductsPage productsPage=new ProductsPage(webDriver);
        productsPage.addSpecificProductToCart();
        List<WebElement> removeButtons = productsPage.getRemoveButtonsList();
        WebElement firstProduct = removeButtons.getFirst();
        assertThat(firstProduct.getText()).isEqualTo("Remove");
    }
}
