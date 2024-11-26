package com.example.FitPeo;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;


import static com.example.FitPeo.CheckBoxes.*;
import static com.example.FitPeo.FitPeoPom.*;

import static com.example.FitPeo.RevenuePage.*;

public class FitPeoTest extends BaseClass{
    WebDriver driver;
    RevenuePage revenuePage;
    CheckBoxes checkBoxes;

@BeforeSuite
    public void webBrowserIntializer(){
   driver= driverIntializer();
}

@Test
    public void mainTest() throws Exception {
    String[] checkBoxArray= {"CPT-99091","CPT-99453","CPT-99454","CPT-99474"};
     revenuePage = websiteIntializer();
    clickOnRevenuePage(driver);
    controlSlider(820);
     checkBoxes = boxValueUpdate(560);
    checkBoxesSelection(checkBoxArray);
    boolean validation = validation();
    if(validation){
        System.out.println(" Test Case Sucessful");
    }else {
        throw  new RuntimeException(" Failed Test Case");
    }
}



    @AfterSuite
    public void teardown(){
  driver.close();
}

}
