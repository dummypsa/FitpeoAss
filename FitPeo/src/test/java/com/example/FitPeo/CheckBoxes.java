package com.example.FitPeo;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

import static com.example.FitPeo.RevenuePage.inputField;
import static com.example.FitPeo.RevenuePage.setInputFiled;



public class CheckBoxes extends BaseClass{
    static JavascriptExecutor js;
    static WebDriver driver;
    static Actions a;
  //  static
    CheckBoxes(WebDriver driver){
        this.driver=driver;
        this.js=(JavascriptExecutor)driver;
        this.a=new Actions(driver);
        PageFactory.initElements(driver,this);
    }

    @FindBy(css=".MuiBox-root.css-1eynrej p:nth-child(1)")
   static List<WebElement> checkboxCheck;

    @FindBy(xpath="//input[@type='checkbox']")
    static List<WebElement> checkBoxTicks;
@FindBy(css=".MuiTypography-root.MuiTypography-body2.inter.css-1xroguk")
static List<WebElement> totalDetails;



    public static void checkBoxesSelection(String[] checkBoxArray) throws Exception {
        scrollDownSlider(js,400);
        int x=0;

        z:		for(int i=0;i<checkboxCheck.size();i++) {
            String nameBox=checkboxCheck.get(i).getText();
            if(nameBox.equalsIgnoreCase(checkBoxArray[x])) {
               int j=checkBoxArray.length-1;
                checkBoxTicks.get(i).click();
                if(x<j) {
                    x++;
                }else {
                    break z;
                }

            }
        }

    }



    public static boolean validation() throws Exception {
        scrollUpSlider(js,950);
        waitForElementToBeVisible(inputField);
        setInputFiled(820); // this is to get value of $110700

        for(int i=0;i<totalDetails.size();i++) {
            String jax=totalDetails.get(i).getText();
            String validaterText="Total Recurring Reimbursement for all Patients Per Month";
            if(jax.contains("Total Recurring Reimbursement for all Patients Per Month")) {
                String stw=	totalDetails.get(i).getText();
                String[] strArr=stw.split(":");
                Assert.assertEquals(validaterText, strArr[0]);
                Assert.assertEquals(strArr[1].trim(),"$110700" );
                Thread.sleep(5000L);
                if(strArr[1].trim().contains("$110700")){
                   return true;
                }
            }
        }
        return false;
    }
}
