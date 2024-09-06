package com.saucedemo.tests;

import com.saucedemo.TestBase;
import com.saucedemo.pages.CartPage;
import com.saucedemo.pages.ProductsPage;
import org.testng.annotations.Test;

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
        productsPage.checkProductImageRedirectToProductDetailPage();
    }

    private static void verifyClickingProductTitleRedirectToCorrectProductDetailPage() {
        ProductsPage productsPage = new ProductsPage(webDriver);
        productsPage.checkProductTitleRedirectToProductDetailPage();
    }


    private static void verifyProductsReorderedLowToHigh() {
        ProductsPage productsPage = new ProductsPage(webDriver);
        productsPage.clickPriceLowToHigh().checkProductsSortedLowToHighPrices();
    }


    private static void verifyProductsReorderedHighToLow() {
        ProductsPage productsPage = new ProductsPage(webDriver);
        productsPage.clickPriceHighToLow().checkProductsSortedHighToLowPrices();
    }


    private static void verifyProductsAreReorderedZtoA() {
        ProductsPage productsPage = new ProductsPage(webDriver);
        productsPage.clickNameZToA().checkProductsSortedZToA();
    }


    private static void verifyProductAreReorderedAtoZ() {
        ProductsPage productsPage = new ProductsPage(webDriver);
        productsPage.clickNameAToZ().checkProductsSortedAToZ();
    }

    private static void verifyCartBadgeCountDeductedByRemovedCount() {
        ProductsPage productsPage = new ProductsPage(webDriver);
        productsPage.addProductToCart().removeProducts().verifyCartIsEmpty();
    }


    private static void verifyProductsSuccessfullyRemovedFromCart() {
        CartPage cartPage=new CartPage(webDriver);
        cartPage.checkCartIsEmpty();
    }

    private static void verifyCartBadgeCountDeductedByOne() {
        ProductsPage productsPage=new ProductsPage(webDriver);
        productsPage.addProductToCart().removeOneProduct();
        productsPage.checkRemoveOneProductUpdateInCartBadge();
    }

    private static void verifyAllAddedProductsDisplayInTheCart() {
        ProductsPage productsPage=new ProductsPage(webDriver);
        productsPage.checkAllProductsAddedToTheCart();
    }

    private static void verifyCartBadgeReflectTheAddedProductCount() {
        ProductsPage productsPage=new ProductsPage(webDriver);
        productsPage.checkCartBadgeIsUpdated();
    }

    private static void verifyAllAddToCartButtonStateChangeToRemove() {
        ProductsPage productsPage=new ProductsPage(webDriver);
        productsPage.addProductToCart().checkAllAddToCartConvertRemoveButtons();
    }

    private static void verifyAddedProductDisplayInTheCart() {
        ProductsPage productsPage=new ProductsPage(webDriver);
        productsPage.checkProductIsAddedToTheCart();
    }

    private static void verifyCartBadgeUpdateToOne() {
        ProductsPage productsPage=new ProductsPage(webDriver);
        productsPage.checkCartBadgeIsOne();
    }

    private static void verifyAddToCartButtonChangeToRemoveState() {
        ProductsPage productsPage=new ProductsPage(webDriver);
        productsPage.addSpecificProductToCart().checkAddToCartConvertToRemove();
    }
}
