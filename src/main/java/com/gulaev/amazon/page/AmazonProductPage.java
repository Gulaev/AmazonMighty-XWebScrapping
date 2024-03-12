package com.gulaev.amazon.page;

import com.gulaev.amazon.entity.AmazonProduct;
import com.gulaev.amazon.service.AmazonProductService;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class AmazonProductPage extends AmazonHomePage {

  private AmazonProductService productService;

  @FindBy(xpath = "//span[@id='productTitle']")
  private ExtendedWebElement title;

  @FindBy(xpath = "//div[@id='ppd']//span[@id='acrCustomerReviewText']")
  private ExtendedWebElement rateCount;

  @FindBy(xpath = "//div[@id='ppd']//span[@id='acrPopover']")
  private ExtendedWebElement starRating;

  public AmazonProductPage(WebDriver driver) {
    super(driver);
    productService = new AmazonProductService();
  }

  private String getStarRating() {
    return starRating.isPresent() ? starRating.getAttribute("title") : "No Rating";
  }

  public AmazonProduct mapTitleAndRatingAndUpdate(AmazonProduct product) {
    product.setTitle(title.getText());
    product.setStarRating(getStarRating());
    String currentRateCount = rateCount.isPresent() ? rateCount.getText() : "No Rate Count";
    product.setRateCount(currentRateCount);
    productService.updateProduct(product);
    return product;
  }
}
