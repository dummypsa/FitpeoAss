package com.example.FitPeo;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.xml.crypto.dsig.spec.ExcC14NParameterSpec;
import java.time.Duration;

public class BaseClass {
    BaseClass(){

    }
        static WebDriver driver;
      static WebDriverWait wait;
     static JavascriptExecutor js;
      static Actions a;
    BaseClass(WebDriver driver){
        this.driver=driver;
        this.js=(JavascriptExecutor)driver;
        this.a=new Actions(driver);
        this.wait=new WebDriverWait(driver, Duration.ofSeconds(10L));
    }
    public static void waitForElementToBeVisible(WebElement locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10L));
        wait.until(ExpectedConditions.visibilityOf(locator));
    }
    public static JavascriptExecutor scrollDownSlider(JavascriptExecutor js, int size) throws Exception {
        js.executeScript("window.scrollBy(0, "+size+")");
        Thread.sleep(5000L); // Added for Output Visibility
        return js;
    }

    public static JavascriptExecutor scrollUpSlider(JavascriptExecutor js, int size) throws Exception {
        js.executeScript("window.scrollBy(0, -"+size+")");
        Thread.sleep(5000L); // Added for Output Visibility
        return js;
    }

}
