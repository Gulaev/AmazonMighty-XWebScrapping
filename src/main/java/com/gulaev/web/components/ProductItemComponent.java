package com.gulaev.web.components;

import com.gulaev.web.entity.Product;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductItemComponent extends AbstractUIObject {

  @FindBy(xpath = ".//div[@data-cy='title-recipe']")
  private ExtendedWebElement title;

  @FindBy(xpath = ".//div[@class='a-row a-size-small']")
  private ExtendedWebElement rate;

  public ProductItemComponent(WebDriver driver, SearchContext searchContext) {
    super(driver, searchContext);
  }

  private String getDateAsin() {
    return getRootExtendedElement().getAttribute("data-asin");
  }

  public Product mapProductTitleAndRate() {
    waitUntil(ExpectedConditions.elementToBeClickable(title), 10);
    waitUntil(ExpectedConditions.elementToBeClickable(rate), 10);

    Product product = new Product();
//    String currentRate = rate.isPresent() ? rate.getText() : "No rating";
    String currentTitle = title.getText();

//    product.setRating(currentRate);
    product.setTitle(currentTitle);
    product.setAsin(getDateAsin());

    return product;
  }
}
