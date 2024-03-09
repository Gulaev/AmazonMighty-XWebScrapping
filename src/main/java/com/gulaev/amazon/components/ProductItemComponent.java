package com.gulaev.amazon.components;

import com.gulaev.amazon.entity.Product;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import java.time.LocalDate;
import java.util.Date;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class ProductItemComponent extends AbstractUIObject {

  @FindBy(xpath = ".//div[@data-cy='title-recipe']")
  private ExtendedWebElement title;

  @FindBy(xpath = ".//div[@class='a-row a-size-small']")
  private ExtendedWebElement rateCount;

  @FindBy(xpath = ".//div[@class='a-row a-size-small']//span[@aria-label and contains(@aria-label, 'out of 5 stars')]")
  private ExtendedWebElement starRating;

  public ProductItemComponent(WebDriver driver, SearchContext searchContext) {
    super(driver, searchContext);
  }

  private String getStarRating() {
    return starRating.getAttribute("aria-label");
  }

  private String getDateAsin() {
    return getRootExtendedElement().getAttribute("data-asin");
  }

  public Product mapProductTitleAndRate() {
    Product product = new Product();
    product.setAsin(getDateAsin());
    String currentRateCount = rateCount.isPresent() ? rateCount.getText() : "No rating";
    String currentStarRate = starRating.isPresent() ? getStarRating() : "No rating";
    String currentTitle = title.getText();
    product.setTitle(currentTitle);
    product.setStarRating(currentStarRate);
    product.setRateCount(currentRateCount);
    product.setUploadedOn(new Date());

    return product;
  }
}
