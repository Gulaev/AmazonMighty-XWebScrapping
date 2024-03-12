package com.gulaev.amazon.page;

import com.gulaev.amazon.components.HeaderComponent;
import com.gulaev.amazon.components.PopUpLocationComponent;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AmazonHomePage extends AbstractPage {

  @FindBy(xpath = "//header[@id='navbar-main']")
  private HeaderComponent header;

  @FindBy(xpath = "//div[@id='a-popover-content-2']")
  private PopUpLocationComponent popUpLocation;

  @FindBy(xpath = "//img[@alt='Amazon.com']")
  private ExtendedWebElement amazonLogo;

  public AmazonHomePage(WebDriver driver) {
    super(driver);
  }

  public void setLocation(String zipCode) {
    header.clickToChoiceLocationButton();
    waitUntil(ExpectedConditions.visibilityOf(popUpLocation.getRootExtendedElement()), 10);
    popUpLocation.setUSALocation(zipCode);
  }

  public boolean ifAmazonLogoPresent() {
    return amazonLogo.isElementPresent();
  }

  public void clickToAmazonLogo() {
    amazonLogo.click();
  }
}
