package com.gulaev.amazon.components;

import com.gulaev.amazon.page.AmazonMerchantItemsPage;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PopUpLocationComponent extends AbstractUIObject {

  @FindBy(id = "GLUXZipUpdateInput")
  private ExtendedWebElement zipCodeInput;

  @FindBy(id = "GLUXZipUpdate")
  private ExtendedWebElement submitZipButton;

  @FindBy(id = "GLUXConfirmClose")
  private ExtendedWebElement confirmCloseButton;

  public PopUpLocationComponent(WebDriver driver, SearchContext searchContext) {
    super(driver, searchContext);
  }

  public AmazonMerchantItemsPage setUSALocation(String zipCode) {
    zipCodeInput.type(zipCode);
    submitZipButton.click();
    waitUntil(ExpectedConditions.visibilityOf(confirmCloseButton.getElement()), 10);
    ((JavascriptExecutor) driver).executeScript("arguments[0].click();",
        confirmCloseButton.getElement());
    return new AmazonMerchantItemsPage(driver);
  }
}