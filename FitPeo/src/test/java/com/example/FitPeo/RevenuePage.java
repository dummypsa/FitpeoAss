package com.example.FitPeo;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class RevenuePage extends BaseClass{
   static WebDriver driver;
    static Actions a;
   static JavascriptExecutor js;
    RevenuePage(WebDriver driver){
        this.driver=driver;
        this.js=(JavascriptExecutor)driver;
        this.a=new Actions(driver);
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath ="//*[text()='Revenue Calculator']")
   public static WebElement revenuePage;

    @FindBy(css="div.MuiBox-root.css-79elbk h4")
    public static WebElement textBlock;

    @FindBy(css="span.MuiSlider-thumb.MuiSlider-thumbSizeMedium.MuiSlider-thumbColorPrimary.MuiSlider-thumb.MuiSlider-thumbSizeMedium.MuiSlider-thumbColorPrimary.css-1sfugkh input")
    public static WebElement valuesSlider;

    @FindBy(css="span.MuiSlider-thumb.MuiSlider-thumbSizeMedium.MuiSlider-thumbColorPrimary.MuiSlider-thumb.MuiSlider-thumbSizeMedium.MuiSlider-thumbColorPrimary.css-1sfugkh")
    public static WebElement sliderBar;

    @FindBy(css="span.MuiSlider-track.css-10opxo5")
    public static WebElement sliderLineWidth;

    @FindBy(css="div.MuiFormControl-root.MuiTextField-root.css-1s5tg4z div input")
    public static WebElement valueBox;

    //                WebElement inputField = driver.findElement(By.cssSelector(".MuiInputBase-input.MuiOutlinedInput-input.MuiInputBase-inputSizeSmall.css-1o6z5ng"));

    @FindBy(css=".MuiInputBase-input.MuiOutlinedInput-input.MuiInputBase-inputSizeSmall.css-1o6z5ng")
    public static WebElement inputField;
    public static void clickOnRevenuePage(WebDriver driver)throws Exception{
        revenuePage.click();
        Thread.sleep(2000L);
        waitForElementToBeVisible(textBlock);
        String str= textBlock.getText();
        Assert.assertEquals(str,"Medicare Eligible Patients");
        if(str.equalsIgnoreCase("Medicare Eligible Patients")) {
            js=scrollDownSlider(js,400);
        }else {
            new RuntimeException("Page not loaded");
        }
    }

    public static void controlSlider(double value) throws Exception {

        String maxValueString = valuesSlider.getAttribute("max");
        double maxValue = Double.parseDouble(maxValueString);
           double targetPercentage = (value / maxValue) * 100;
        js.executeScript("arguments[0].value = arguments[1];", valuesSlider, value);
        js.executeScript("arguments[0].style.left = arguments[1] + '%';", sliderBar, targetPercentage);
        js.executeScript("arguments[0].dispatchEvent(new Event('input'));", valuesSlider);
        String sliderBarStyle = sliderBar.getAttribute("style");
        String leftPercentageString = sliderBarStyle.split(";")[0].split(":")[1].trim().replace("%", "");
        double leftPercentage = Double.parseDouble(leftPercentageString);
        js.executeScript("arguments[0].style.width = arguments[1] + '%';", sliderLineWidth, leftPercentage);
        usingActions(value);
    }

    private static void usingActions(double value) throws Exception {
        String maxValueString = valuesSlider.getAttribute("max");
        double maxValue = Double.parseDouble(maxValueString);
        String currentValueString = valuesSlider.getAttribute("value");
        double currentValue = Double.parseDouble(currentValueString);
           double targetPercentage = (value / maxValue) * 100;
        targetPercentage = Math.round(targetPercentage);
        double sliderWidth = sliderBar.getSize().getWidth();
        double targetPosition = (targetPercentage / 100) * sliderWidth;
        double currentPosition = (currentValue / maxValue) * sliderWidth;
        int offset = (int) (targetPosition - currentPosition);
        a.clickAndHold(sliderBar)
                .moveByOffset(offset, 0)
                .release()
                .perform();
        String sliderBarStyle = sliderBar.getAttribute("style");
        String leftPercentageString = sliderBarStyle.split(";")[0].split(":")[1].trim().replace("%", "");
        double leftPercentage = Double.parseDouble(leftPercentageString);
        js.executeScript("arguments[0].setAttribute('style', 'width: " + leftPercentage + "%;')", sliderLineWidth);
    Thread.sleep(2000L);
    }

    public static CheckBoxes boxValueUpdate(int valueOfBox) throws Exception {
        boxValue();
        Thread.sleep(2000L);
        setInputFiled(valueOfBox);
return new CheckBoxes(driver);
    }



private static void boxValue() throws Exception{
    String boxValue=valueBox.getAttribute("value");
    double d=Double.parseDouble(boxValue);
    if(d!=0) {
        setInputFiled(0);
    }
}
static void setInputFiled(int value) throws Exception {
    inputField.click();
    inputField.clear();
String values= String.valueOf(value);
    a.moveToElement(inputField)  // Move to the input box
            .click()  // Click to focus the field
            .keyDown(Keys.CONTROL)  // Hold down the Ctrl key
            .sendKeys("a")  // Press 'A' to select all text
            .keyUp(Keys.CONTROL)  // Release the Ctrl key
            .sendKeys(Keys.DELETE)  // Press 'Delete' to clear the text
            .perform();

    inputField.sendKeys(values);
    Thread.sleep(2000L);
}




}

