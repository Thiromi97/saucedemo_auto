package com.saucedemo.tests;

import com.saucedemo.TestBase;
import com.saucedemo.pages.CartPage;
import com.saucedemo.pages.ProductsPage;
import org.testng.annotations.Test;

public class ProductTest extends TestBase {

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
        ProductsPage productsPage=new ProductsPage(webDriver);
        productsPage.clickCart();
        CartPage cartPage = new CartPage(webDriver);
        cartPage.verifyNavigationToCartPage();
    }

    private static void verifyClickingProductImageRedirectToCorrectProductDetailPage() {
        ProductsPage productsPage = new ProductsPage(webDriver);
        productsPage.verifyProductImageRedirectToProductDetailPage();
    }

    private static void verifyClickingProductTitleRedirectToCorrectProductDetailPage() {
        ProductsPage productsPage = new ProductsPage(webDriver);
        productsPage.verifyProductTitleRedirectToProductDetailPage();
    }


    private static void verifyProductsReorderedLowToHigh() {
        ProductsPage productsPage = new ProductsPage(webDriver);
        productsPage.clickPriceLowToHigh().verifyProductsSortedLowToHighPrices();
    }


    private static void verifyProductsReorderedHighToLow() {
        ProductsPage productsPage = new ProductsPage(webDriver);
        productsPage.clickPriceHighToLow().verifyProductsSortedHighToLowPrices();
    }


    private static void verifyProductsAreReorderedZtoA() {
        ProductsPage productsPage = new ProductsPage(webDriver);
        productsPage.clickNameZToA().verifyProductsSortedZToA();
    }


    private static void verifyProductAreReorderedAtoZ() {
        ProductsPage productsPage = new ProductsPage(webDriver);
        productsPage.clickNameAToZ().verifyProductsSortedAToZ();
    }

    private static void verifyCartBadgeCountDeductedByRemovedCount() {
        ProductsPage productsPage = new ProductsPage(webDriver);
        productsPage.addProductToCart().removeProducts().verifyCartIsEmpty();
    }


    private static void verifyProductsSuccessfullyRemovedFromCart() {
        CartPage cartPage=new CartPage(webDriver);
        cartPage.verifyCartHasNoItems();
    }

    private static void verifyProductIsRemovedFromTheCart() {
        CartPage cartPage=new CartPage(webDriver);
        cartPage.verifyCartItemsDeductByOne();
    }

    private static void goToCartPage() {
        ProductsPage productsPage = new ProductsPage(webDriver);
        productsPage.clickCart();
    }

    private static void verifyCartBadgeCountDeductedByOne() {
        ProductsPage productsPage=new ProductsPage(webDriver);
        productsPage.addProductToCart().removeOneProduct();
        productsPage.verifyRemoveOneProductUpdateInCartBadge();
    }

    private static void verifyAllAddedProductsDisplayInTheCart() {
        ProductsPage productsPage=new ProductsPage(webDriver);
        productsPage.verifyAllProductsAddedToTheCart();
    }

    private static void verifyCartBadgeReflectTheAddedProductCount() {
        ProductsPage productsPage=new ProductsPage(webDriver);
        productsPage.verifyCartBadgeUpdate();
    }

    private static void verifyAllAddToCartButtonStateChangeToRemove() {
        ProductsPage productsPage=new ProductsPage(webDriver);
        productsPage.addProductToCart().verifyAllAddToCartConvertRemoveButtons();
    }

    private static void verifyAddedProductDisplayInTheCart() {
        ProductsPage productsPage=new ProductsPage(webDriver);
        productsPage.verifyProductIsAddedToTheCart();
    }

    private static void verifyCartBadgeUpdateToOne() {
        ProductsPage productsPage=new ProductsPage(webDriver);
        productsPage.verifyCartBadgeIsOne();
    }

    private static void verifyAddToCartButtonChangeToRemoveState() {
        ProductsPage productsPage=new ProductsPage(webDriver);
        productsPage.addSpecificProductToCart().verifyAddToCartConvertToRemove();
    }
}
