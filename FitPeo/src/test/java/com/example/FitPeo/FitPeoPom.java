package com.example.FitPeo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class FitPeoPom extends BaseClass{


   static WebDriver driver;

    FitPeoPom(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    public static WebDriver driverIntializer(){
        System.setProperty("webdriver.chromer.driver", System.getProperty("user.dir") +"src/test/resources/chromedriver.exe");
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10L));
        return  driver;
    }


    public static RevenuePage websiteIntializer(){
        driver.get("https://www.fitpeo.com/");
        return new RevenuePage(driver);
    }

}
