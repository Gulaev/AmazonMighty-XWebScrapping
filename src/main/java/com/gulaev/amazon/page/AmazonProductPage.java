package com.gulaev.amazon.page;

import com.gulaev.amazon.entity.AmazonProduct;
import com.gulaev.amazon.service.AmazonProductService;
import com.gulaev.amazon.service.SheetsLinkService;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import java.util.List;
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

  @FindBy(xpath = "//*[@id='zeitgeistBadge_feature_div']//i[@class='a-icon a-icon-addon p13n-best-seller-badge']")
  private ExtendedWebElement rank;

  @FindBy(xpath = "//*[@id=\"detailBulletsWrapper_feature_div\"]//ul[@class=\"a-unordered-list a-nostyle a-vertical zg_hrsr\"]//span")
  private List<ExtendedWebElement> bestSellerRankUS;

  @FindBy(xpath = "")
  private List<ExtendedWebElement> bestSellerRankUK;

  public AmazonProductPage(WebDriver driver) {
    super(driver);
    this.productService = new AmazonProductService();
    this.sheetsLinkService = new SheetsLinkService();
  }

  private String getStarRating() {
    return starRating.isPresent() ? starRating.getAttribute("title") : "No Rating";
  }

  private String getRank() {
    if (rank.isPresent()) {
      return rank.getText();
    } else {
      return "No Rank";
    }
  }

  private String getBestSellerRank() {
    StringBuilder rankMessage = new StringBuilder();
    if (!bestSellerRankUK.isEmpty()) {
      for (ExtendedWebElement rank: bestSellerRankUK) {
        rankMessage.append(rank.getText());
        rankMessage.append("/n");
      }
    } else if (!bestSellerRankUS.isEmpty()) {
      bestSellerRankUK.forEach(e -> {rankMessage.append(e.getText());
      rankMessage.append("/n");});

    } else {
      rankMessage.append("No Rank");
    }

    return rankMessage.toString();
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
    product.setRank(getRank());
    product.setBestSellerRank(getBestSellerRank());
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
