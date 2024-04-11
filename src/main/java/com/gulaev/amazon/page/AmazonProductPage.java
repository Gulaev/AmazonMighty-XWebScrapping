package com.gulaev.amazon.page;

import com.gulaev.amazon.entity.AmazonProduct;
import com.gulaev.amazon.service.AmazonProductService;
import com.gulaev.amazon.service.SheetsLinkService;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class AmazonProductPage extends AmazonHomePage {

  private AmazonProductService productService;
  private SheetsLinkService sheetsLinkService;

  @FindBy(xpath = "//span[@id='productTitle']")
  private ExtendedWebElement title;

  @FindBy(xpath = "//div[@id='ppd']//span[@id='acrCustomerReviewText']")
  private ExtendedWebElement rateCount;

  @FindBy(xpath = "//div[@id='ppd']//span[@id='acrPopover']")
  private ExtendedWebElement starRating;

  @FindBy(id = "bylineInfo")
  private ExtendedWebElement shopTitle;

  @FindBy(xpath = "//div[@id=\"rightCol\"]//span[@class=\"a-offscreen\"][1]")
  private ExtendedWebElement price;

  public AmazonProductPage(WebDriver driver) {
    super(driver);
    this.productService = new AmazonProductService();
    this.sheetsLinkService = new SheetsLinkService();
  }

  private String getStarRating() {
    return starRating.isPresent() ? starRating.getAttribute("title") : "No Rating";
  }

  public AmazonProduct mapTitleAndRatingAndUpdate(AmazonProduct product) {
    product.setTitle(title.getText());
    product.setStarRating(getStarRating());
    String currentRateCount = rateCount.isPresent() ? rateCount.getText() : "No Rate Count";
//    product.setShopTitle(getShopTitle(product.getShopName()));
    product.setRateCount(currentRateCount);
    product.setSheetLink(sheetsLinkService.getSheetsLinkByAsinAndShopName(product.getAsin(),
        product.getShopTitle()));
    product.setPrice(getPrice());
    productService.updateProduct(product);
    return product;
  }

  private String getShopTitle(String shopName) {
    String text = shopTitle.getText();
    if (text.contains("ZOROM'S")) {
      return "ZOROM'S";
    } else if (text.contains("Mighty")) {
      if (shopName.equals("Amazon.com")) {
        return "Mighty-X US";
      } else if (shopName.equals("Amazon.co.uk")) {
        return "Mighty-X UK";
      }
    } else if (text.contains("MYHELP")) {
      return "Kivals";
    }
    return "";
  }

  public String getPrice() {
    // Execute JavaScript to get the text of the element
    JavascriptExecutor jsExecutor = (JavascriptExecutor) getDriver();
    String price = (String) jsExecutor.executeScript(
        "return arguments[0].textContent.trim();",
        findExtendedWebElement(
            By.xpath("//div[@id=\"rightCol\"]//span[@class=\"a-offscreen\"][1]"))
            .getElement());
    // Return the price
    return price;
  }

}
