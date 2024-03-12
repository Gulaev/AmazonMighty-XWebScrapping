package com.gulaev.amazon.page;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class AmazonProductPage extends AbstractPage {

  @FindBy(xpath = "//span[@id='productTitle']")
  private ExtendedWebElement title;

  @FindBy(xpath = "//div[@id='ppd']//span[@id='acrCustomerReviewText']")
  private ExtendedWebElement rateCount;

  @FindBy(xpath = "")
  private ExtendedWebElement starRating;

  public AmazonProductPage(WebDriver driver) {
    super(driver);
  }
}
