package com.gulaev;

import com.gulaev.amazon.entity.AmazonProduct;
import com.gulaev.amazon.page.AmazonProductsPage;
import com.gulaev.amazon.service.AmazonProductService;
import com.zebrunner.carina.core.AbstractTest;
import com.zebrunner.carina.utils.R;
import java.util.ArrayList;
import java.util.List;
import org.testng.annotations.Test;

public class ScrapAmazonData extends AbstractTest {

  private final String MIGTHY_X_US_URL = R.CONFIG.get("urlMightyXUS");
  private final String MIGTHY_X_UK_URL = R.CONFIG.get("urlMightyXUK");
  private final String ZOROMS_US_URL = R.CONFIG.get("urlZoromsUS");
  private final String KIVALS_US_URL = R.CONFIG.get("urlKivalsUS");
  private final String AMAZON_HOME_PAGE_UK = R.CONFIG.get("urlUKAmazonHomePage");

  @Test
  public void getDataFromMigthyXUS() {
    String shopName = "Migthy-X US";
    AmazonProductService amazonProductService = new AmazonProductService();
    AmazonProductsPage amazonProductsPage = new AmazonProductsPage(getDriver());
    amazonProductsPage.openURL(MIGTHY_X_US_URL);
    if (amazonProductsPage.ifAmazonLogoPresent()) {
      amazonProductsPage.clickToAmazonLogo();
      amazonProductsPage.openURL(MIGTHY_X_US_URL);
    }
    amazonProductsPage.setLocation("97015");
    List<AmazonProduct> amazonProducts = new ArrayList<>();
    do {
      amazonProductsPage.scrollToNextPageButton();
      List<AmazonProduct> currentListAmazonProducts = amazonProductsPage
          .getProductsAndSetShop(shopName);
      amazonProducts.addAll(currentListAmazonProducts);
      amazonProducts.forEach(System.out::println);
      if (amazonProductsPage.ifNextPageIsPresent()) {
        amazonProductsPage = amazonProductsPage.goToNextPage();
      } else {
        amazonProductService.updateProductByShop(amazonProducts, shopName);
        break;
      }
    } while (true);
  }

  @Test
  public void getDataFromMigthyXUK() {
    AmazonProductService amazonProductService = new AmazonProductService();
    String shopName = "Migthy-X UK";
    AmazonProductsPage amazonProductsPage = new AmazonProductsPage(getDriver());
    amazonProductsPage.openURL(AMAZON_HOME_PAGE_UK);
    amazonProductsPage.openURL(MIGTHY_X_UK_URL);
    amazonProductsPage.openURL(AMAZON_HOME_PAGE_UK);
    amazonProductsPage.openURL(MIGTHY_X_UK_URL);

    if (amazonProductsPage.ifAmazonLogoPresent()) {
      amazonProductsPage.clickToAmazonLogo();
      amazonProductsPage.openURL(MIGTHY_X_UK_URL);
    }
    amazonProductsPage.setLocation("NW7 1SW");
    amazonProductsPage.openURL(MIGTHY_X_UK_URL);
    List<AmazonProduct> amazonProducts = new ArrayList<>();
    do {
      amazonProductsPage.scrollToNextPageButton();
      List<AmazonProduct> currentPageAmazonProducts = amazonProductsPage
          .getProductsAndSetShop(shopName);
      amazonProducts.addAll(currentPageAmazonProducts);
      amazonProducts.forEach(System.out::println);
      if (amazonProductsPage.ifNextPageIsPresent()) {
        amazonProductsPage = amazonProductsPage.goToNextPage();
      } else {
        amazonProductService.updateProductByShop(amazonProducts, shopName);
        break;
      }
    } while (true);
  }

  @Test
  public void getDataFromKivals() {
    String shopName = "Kivals US";
    AmazonProductService amazonProductService = new AmazonProductService();
    AmazonProductsPage amazonProductsPage = new AmazonProductsPage(getDriver());
    amazonProductsPage.openURL(KIVALS_US_URL);
    if (amazonProductsPage.ifAmazonLogoPresent()) {
      amazonProductsPage.clickToAmazonLogo();
      amazonProductsPage.openURL(KIVALS_US_URL);
    }
    amazonProductsPage.setLocation("97015");
    List<AmazonProduct> amazonProducts = new ArrayList<>();
    do {
      amazonProductsPage.scrollToNextPageButton();
      List<AmazonProduct> currentPageAmazonProducts = amazonProductsPage
          .getProductsAndSetShop(shopName);
      amazonProducts.addAll(currentPageAmazonProducts);
      amazonProducts.forEach(System.out::println);
      if (amazonProductsPage.ifNextPageIsPresent()) {
        amazonProductsPage = amazonProductsPage.goToNextPage();
      } else {
        amazonProductService.updateProductByShop(amazonProducts, shopName);
        break;
      }
    } while (true);
  }

  @Test
  public void getDataFromZoroms() {
    String shopName = "Zoroms US";
    AmazonProductService amazonProductService = new AmazonProductService();
    AmazonProductsPage amazonProductsPage = new AmazonProductsPage(getDriver());
    amazonProductsPage.openURL(ZOROMS_US_URL);
    if (amazonProductsPage.ifAmazonLogoPresent()) {
      amazonProductsPage.clickToAmazonLogo();
      amazonProductsPage.openURL(ZOROMS_US_URL);
    }
    amazonProductsPage.setLocation("97015");
    List<AmazonProduct> amazonProducts = new ArrayList<>();
    do {
      amazonProductsPage.scrollToNextPageButton();
      List<AmazonProduct> currentPageAmazonProducts = amazonProductsPage
          .getProductsAndSetShop(shopName);
      amazonProducts.addAll(currentPageAmazonProducts);
      amazonProducts.forEach(System.out::println);
      if (amazonProductsPage.ifNextPageIsPresent()) {
        amazonProductsPage = amazonProductsPage.goToNextPage();
      } else {
        amazonProductService.updateProductByShop(amazonProducts, shopName);
        break;
      }
    } while (true);
  }
}
