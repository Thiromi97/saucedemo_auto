package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class HomePage {
    final WebDriver webDriver;

    @FindBy(css="span.title")
    WebElement txtProductPageTitle;


    public HomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver,this);
    }

    public void verifySuccessfullNavigationToProductPage() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        WebElement productTitle = wait.until(ExpectedConditions.visibilityOf(txtProductPageTitle));
        assertThat(productTitle.getText()).isEqualTo("Products");
    }

    public void verifyProductImagesDuplication() {
        //List all product images
        List<WebElement> productImages = webDriver.findElements(By.cssSelector(".inventory_item img"));
        //Create a Hash Set that handle duplication(it is  a set where every item is unique)
        Set<String> imageSources = new HashSet<>();
        for (WebElement image : productImages) {
            imageSources.add(image.getAttribute("src"));
        }
        if (imageSources.size() == 1) {
            System.out.println("All product images are same as expected.");
        } else {
            System.out.println("Product images vary, which is unexpected for problem_user.");
        }
    }
}
