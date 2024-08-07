package com.gulaev.amazon.page;

import com.gulaev.amazon.entity.AmazonProduct;
import com.gulaev.amazon.entity.SheetsLink;
import com.gulaev.amazon.service.AmazonProductService;
import com.gulaev.amazon.service.SheetsLinkService;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
  private List<ExtendedWebElement> bestSellerRankUK;

  @FindBy(xpath = "//table//tr[th[contains(text(), 'Best Sellers Rank')]]//span//span")
  private List<ExtendedWebElement> bestSellerRankUS;

  public AmazonProductPage(WebDriver driver) {
    super(driver);
    this.productService = new AmazonProductService();
    this.sheetsLinkService = new SheetsLinkService();
  }

  private String getStarRating() {
    return starRating.isPresent() ? starRating.getAttribute("title") : "No Rating";
  }

  private String getRank() {
//    if (rank.isPresent()) {
//      return "#1 Best Seller";
//    } else {
//      return "No Rank";
//    }
    WebDriver driver = getDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

    try {
      WebElement rankElement = wait.until(
          ExpectedConditions.presenceOfElementLocated(
              By.xpath("//*[@id='zeitgeistBadge_feature_div']//i[@class='a-icon a-icon-addon p13n-best-seller-badge']")));
      return "#1 Best Seller";
    } catch (TimeoutException e) {
      return "No Rank";
    }
  }

  private String getBestSellerRank() {
    StringBuilder rankMessage = new StringBuilder();
    scrollDown();
    if (!bestSellerRankUS.isEmpty()) {
      for (ExtendedWebElement rank: bestSellerRankUS) {
        rankMessage.append(rank.getText());
        rankMessage.append("/n");
      }
    } else if (!bestSellerRankUK.isEmpty()) {
      for (ExtendedWebElement rank: bestSellerRankUK) {
        rankMessage.append(rank.getText());
        rankMessage.append("/n");
      }
    } else {
      rankMessage.append("No Rank");
    }
    return rankMessage.toString();
  }

  private void scrollDown() {
    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("window.scrollBy(0,10000)");
  }

  public AmazonProduct mapTitleAndRatingAndUpdate(AmazonProduct product) {
    SheetsLink productLink = sheetsLinkService.getLinkByAsinAndShopTitle(product.getAsin(), product.getShopTitle());
//    product.setTitle(title.getText());
    product.setStarRating(getStarRating());
    String currentRateCount = rateCount.isPresent() ? rateCount.getText() : "No Rate Count";
//    product.setShopTitle(getShopTitle(product.getShopName()));
    product.setRateCount(currentRateCount);
    product.setSheetLink(sheetsLinkService.getSheetsLinkByAsinAndShopName(product.getAsin(),
        product.getShopTitle()));
    product.setPrice(getPrice());
    product.setRank(getRank());
    product.setBestSellerRank(getBestSellerRank());
    product.setTitle(productLink.getSrTitle());
    productService.updateProduct(product);
    return product;
  }

//  private String getShopTitle(String shopName) {
//    String text = shopTitle.getText();
//    if (text.contains("ZOROM'S")) {
//      return "ZOROM'S";
//    } else if (text.contains("Mighty")) {
//      if (shopName.equals("Amazon.com")) {
//        return "Mighty-X US";
//      } else if (shopName.equals("Amazon.co.uk")) {
//        return "Mighty-X UK";
//      }
//    } else if (text.contains("MYHELP")) {
//      return "Kivals";
//    }
//    return "";
//  }

  public String getPrice() {
    JavascriptExecutor jsExecutor = (JavascriptExecutor) getDriver();
    String price = (String) jsExecutor.executeScript(
        "return arguments[0].textContent.trim();",
        findExtendedWebElement(
            By.xpath("//div[@id=\"rightCol\"]//span[@class=\"a-offscreen\"][1]"))
            .getElement());
    return price;
  }

}
