package com.gulaev;

import com.gulaev.amazon.entity.AmazonProduct;
import com.gulaev.amazon.page.AmazonProductPage;
import com.gulaev.amazon.service.AmazonProductService;
import com.gulaev.amazonSessions.service.AmazonSessionService;
import com.gulaev.hivemind.component.HivemindProductItemComponent;
import com.gulaev.hivemind.entity.HivemindItem;
import com.gulaev.hivemind.page.HivemideHomePage;
import com.gulaev.hivemind.page.HivemindLoginPage;
import com.gulaev.hivemind.service.HivemindService;
import com.zebrunner.carina.core.AbstractTest;
import com.zebrunner.carina.utils.R;
import java.util.ArrayList;
import java.util.List;
import org.testng.annotations.Test;

public class HiveMindScrapData extends AbstractTest {

  private final String HIVE_MIND_URL = R.CONFIG.get("urlHiveMind");
  private final String EMAIL = R.TESTDATA.get("email");
  private final String PASSWORD = R.TESTDATA.get("password");
  private final String AMAZON_HOME_PAGE_UK = R.CONFIG.get("urlUKAmazonHomePage");
  private final String AMAZON_HOME_PAGE = R.CONFIG.get("urlAmazonHomePage");
  private final String MIGTHY_X_UK_URL = R.CONFIG.get("urlMightyXUK");
  private final String KIVALS_US_URL = R.CONFIG.get("urlKivalsUS");

  @Test
  public void scrapDataHivemind() {
    HivemindService hivemindService = new HivemindService();
    HivemindLoginPage hivemindLoginPage = new HivemindLoginPage(getDriver());
    hivemindLoginPage.openURL(HIVE_MIND_URL);
    HivemideHomePage homePage = hivemindLoginPage.login(EMAIL, PASSWORD);
    List<HivemindItem> productItems = new ArrayList<>();

    do {
      pause(60);
      List<HivemindItem> itemsOnPage = homePage.getProductItems().stream()
          .map(HivemindProductItemComponent::mapItem).toList();
      productItems.addAll(itemsOnPage);

      if (homePage.isNextPagePresent()) {
        homePage.goToNextPage();
        pause(5);
      } else {
        break;
      }
    } while (true);

    productItems.forEach(System.out::println);
    hivemindService.checkingReconciliationAndLoad(productItems);
  }

  @Test
  public void scrapDataFromAmazonUS() {
    AmazonProductPage productPage = new AmazonProductPage(getDriver());
    AmazonProductService amazonProductService = new AmazonProductService();
    List<AmazonProduct> productWithoutRating = amazonProductService
        .getProductsWhereTitleNullAndShopName("Amazon.com");
    if (!productWithoutRating.isEmpty()) {
      productPage.openURL(KIVALS_US_URL);
      if (productPage.ifAmazonLogoPresent()) {
        productPage.clickToAmazonLogo();
        productPage.openURL(KIVALS_US_URL);
      }
      productPage.setLocation("97015");
      for (AmazonProduct product : productWithoutRating) {
        productPage.openURL(AMAZON_HOME_PAGE + "dp/" + product.getAsin());
        AmazonProduct currentProduct = productPage.mapTitleAndRatingAndUpdate(product);
        System.out.println(currentProduct);
      }
    } else {
      System.out.println("All products have already been scraped.");
    }
  }

  @Test
  public void scrapDataFromAmazonUK() {
    AmazonProductPage productPage = new AmazonProductPage(getDriver());
    AmazonProductService amazonProductService = new AmazonProductService();
    List<AmazonProduct> productWithoutRating = amazonProductService
        .getProductsWhereTitleNullAndShopName("Amazon.co.uk");
    if (!productWithoutRating.isEmpty()) {
      productPage.openURL(AMAZON_HOME_PAGE);
      productPage.openURL(AMAZON_HOME_PAGE_UK);
      productPage.openURL(MIGTHY_X_UK_URL);
      productPage.openURL(AMAZON_HOME_PAGE_UK);
      productPage.openURL(MIGTHY_X_UK_URL);

      if (productPage.ifAmazonLogoPresent()) {
        productPage.clickToAmazonLogo();
        productPage.openURL(MIGTHY_X_UK_URL);
      }
      productPage.setLocation("NW7 1SW");
      for (AmazonProduct product : productWithoutRating) {
        productPage.openURL(AMAZON_HOME_PAGE_UK + "dp/" + product.getAsin());
        AmazonProduct currentProduct = productPage.mapTitleAndRatingAndUpdate(product);
        System.out.println(currentProduct);
      }
    } else {
      System.out.println("All products have already been scraped.");
    }
  }

  @Test
  public void scrapSessions() {
    AmazonSessionService sessionService = new AmazonSessionService();
    sessionService.getSessionForUS();
    sessionService.getSessionForUK();
  }
}
