package com.saucedemo.tests;

import com.saucedemo.Product;
import com.saucedemo.TestBase;
import com.saucedemo.pages.CartPage;
import com.saucedemo.pages.ProductDetailPage;
import com.saucedemo.pages.ProductsPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductTests extends TestBase {
    private static final Logger logger = LogManager.getLogger(ProductTests.class);
    @Test
    public void verifyAddToCartFunctionalityForOneProduct(){
        logger.info("Starting test: verifyAddToCartFunctionalityForOneProduct");
        try {
            loginAsStandardUser();
            verifyAddToCartButtonChangeToRemoveState();
            logger.info("AddToCart button change to remove state");
        } catch (Exception e) {
            logger.error("Unexpected error during addToCart functionality verification for one product ", e);
        }
        try {
            verifyCartBadgeUpdateToOne();
            logger.info("Cart Badge display as one");
        } catch (Exception e) {
            logger.error("Unexpected error during addToCart functionality verification for one product ", e);
        }
        try {
            verifyAddedProductDisplayInTheCart();
            logger.info("Added Product displayed successfully in the cart");
        } catch (Exception e) {
            logger.error("Unexpected error during addToCart functionality verification for one product ", e);
        }

    }

    @Test
    public void verifyAddToCartFunctionalityForMultipleProducts(){
        logger.info("Starting test: verifyAddToCartFunctionalityForMultipleProducts");
        loginAsStandardUser();
        verifyAllAddToCartButtonStateChangeToRemove();
        verifyCartBadgeReflectTheAddedProductCount();
        verifyAllAddedProductsDisplayInTheCart();
    }

    @Test
    public void verifyRemoveButtonFunctionalityForOneProduct(){
        logger.info("Starting test: verifyRemoveButtonFunctionalityForOneProduct");
        loginAsStandardUser();
        verifyCartBadgeCountDeductedByOne();
        goToCartPage();
        verifyProductIsRemovedFromTheCart();
    }

    @Test
    public void verifyRemoveButtonFunctionalityForMultipleProducts(){
        logger.info("Starting test: verifyRemoveButtonFunctionalityForMultipleProducts");
        try {
            loginAsStandardUser();
            verifyCartBadgeCountDeductedByRemovedCount();
            logger.info("Removed product count updated in cart badge");
        } catch (Exception e) {
            logger.error("Unexpected error during remove button functionality verification for multiple products ", e);
        }
        try {
            goToCartPage();
            verifyProductsSuccessfullyRemovedFromCart();
            logger.info("Products successfully removed from cart");
        } catch (Exception e) {
            logger.error("Unexpected error during remove button functionality verification for multiple products ", e);
        }

    }

    @Test
    public void verifySortProductsByNameAtoZ(){
        logger.info("Starting test: verifySortProductsByNameAtoZ");try {
            loginAsStandardUser();
            verifyProductAreReorderedAtoZ();
            logger.info("Products are reordered A to Z");
        } catch (Exception e) {
            logger.error("Unexpected error during sort functionality verification for Name(AtoZ) ", e);
        }

    }

    @Test
    public void verifySortProductsByNameZtoA(){
        logger.info("Starting test: verifySortProductsByNameZtoA");
        loginAsStandardUser();
        verifyProductsAreReorderedZtoA();
    }

    @Test
    public void verifySortProductsByPriceLowToHigh(){
        logger.info("Starting test: verifySortProductsByPriceLowToHigh");
        loginAsStandardUser();
        verifyProductsReorderedLowToHigh();
    }

    @Test
    public void verifySortProductsByPriceHighToLow(){
        logger.info("Starting test: verifySortProductsByPriceHighToLow");
        loginAsStandardUser();
        verifyProductsReorderedHighToLow();
    }

    @Test
    public void verifyViewProductDetailsByProductTitle(){
        logger.info("Starting test: verifyViewProductDetailsByProductTitle");
        loginAsStandardUser();
        verifyClickingProductTitleRedirectToCorrectProductDetailPage();
    }

    @Test
    public void verifyViewProductDetailsByProductImage(){
        logger.info("Starting test: verifyViewProductDetailsByProductImage");
        loginAsStandardUser();
        verifyClickingProductImageRedirectToCorrectProductDetailPage();
    }

    @Test
    public void verifyShoppingCartButtonFunctionality(){
        logger.info("Starting test: verifyShoppingCartButtonFunctionality");
        loginAsStandardUser();
        goToCartPage();
        verifyCartPageLoadedSuccessfully();
    }

    private static void verifyClickingProductImageRedirectToCorrectProductDetailPage() {
        ProductsPage productsPage = new ProductsPage(webDriver);
        List<WebElement> productImages = productsPage.getProductImagesList();
        List<Product> products = productsPage.extractProducts();
        logger.info("Get Product List");
        int productsSize = products.size();
        for (int i = 0; i < productsSize; i++) {
            Product product = products.get(i);
            logger.info("Get Product {}",i);
            WebElement productImage = productsPage.getProductImage();
            String txtProductName = product.getName();
            logger.info("Get Product {} name: {}", i, txtProductName);
            String txtProductDescription = product.getDescription();
            logger.info("Get Product {} description: {}", i, txtProductDescription);
            String txtProductPrice = product.getPrice();
            logger.info("Get Product {} price: {}", i, txtProductPrice);
            String txtProductImage = product.getImageSrc();
            logger.info("Get Product {} imageSrc: {}",i,txtProductImage);
            productImage.click();
            logger.info("Click Product{} Image",i);
            ProductDetailPage productDetailPage = new ProductDetailPage(webDriver);
            String txtProductDetailName = productDetailPage.getProductName();
            logger.info("Get ProductDetail {} name: {}", i,txtProductDetailName);
            assertThat(txtProductDetailName).isEqualTo(txtProductName);
            String txtProductDetailDescription = productDetailPage.getProductDescription();
            logger.info("Get ProductDetail {} description: {}", i, txtProductDetailDescription);
            assertThat(txtProductDetailDescription).isEqualTo(txtProductDescription);
            String txtProductDetailPrice = productDetailPage.getProductPrice();
            logger.info("Get ProductDetail {} price: {}", i, txtProductDetailPrice);
            assertThat(txtProductDetailPrice).isEqualTo(txtProductPrice);
            String txtProductDetailImageSrc = productDetailPage.getProductImageSrc();
            logger.info("Get ProductDetail {} imageSrc: {}",i,txtProductDetailImageSrc);
//            assertThat(txtProductDetailImageSrc).isEqualTo(txtProductImage);
            webDriver.navigate().back();
        }
    }


    @Test
    public static void verifyClickingProductTitleRedirectToCorrectProductDetailPage() {
        ProductsPage productsPage = new ProductsPage(webDriver);
        List<WebElement> productTitles = productsPage.getProductNamesList();
        List<Product> products = productsPage.extractProducts();
        int productsSize = products.size();
        for (int i = 0; i < productsSize; i++) {
            Product product = products.get(i);
            WebElement productName = productTitles.get(i);
            String txtProductName = product.getName();
            String txtProductDescription = product.getDescription();
            String txtProductPrice = product.getPrice();
            String txtProductImage = product.getImageSrc();
            productName.click();
            ProductDetailPage productDetailPage = new ProductDetailPage(webDriver);
            String txtProductDetailName = productDetailPage.getProductName();
            assertThat(txtProductDetailName).isEqualTo(txtProductName);
            String txtProductDetailImageSrc = productDetailPage.getProductImageSrc();
            assertThat(txtProductDetailImageSrc).isEqualTo(txtProductImage);
            String txtProductDetailDescription = productDetailPage.getProductDescription();
            assertThat(txtProductDetailDescription).isEqualTo(txtProductDescription);
            String txtProductDetailPrice = productDetailPage.getProductPrice();
            assertThat(txtProductDetailPrice).isEqualTo(txtProductPrice);
            webDriver.navigate().back();
        }
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
        Collections.sort(expectedNames.reversed());
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
