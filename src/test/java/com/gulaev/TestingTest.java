package com.gulaev;

import com.gulaev.web.entity.Product;
import com.gulaev.web.page.AmazonProductsPage;
import com.zebrunner.carina.core.AbstractTest;
import com.zebrunner.carina.utils.R;
import java.util.List;
import org.testng.annotations.Test;

public class TestingTest extends AbstractTest {

  private final String MIGTHY_X_US_URL = R.CONFIG.get("urlMightyXUS");
  private final String MIGTHY_X_UK_URL = R.CONFIG.get("urlMightyXUK");
  private final String ZOROMS_US_URL = R.CONFIG.get("urlZoromsUS");
  private final String KIVALS_US_URL = R.CONFIG.get("urlKivalsUS");

  @Test
  public void getDataFromMigthyXUS() {
    AmazonProductsPage amazonProductsPage = new AmazonProductsPage(getDriver());
    amazonProductsPage.openURL(MIGTHY_X_US_URL);
    if (amazonProductsPage.ifAmazonLogoPresent()) {
      amazonProductsPage.clickToAmazonLogo();
      amazonProductsPage.openURL(MIGTHY_X_US_URL);
    }
    amazonProductsPage.setLocation("97015");
    do {
      amazonProductsPage.scrollToNextPageButton();
      List<Product> products = amazonProductsPage.getProducts();
      products.forEach(System.out::println);
      if (amazonProductsPage.ifNextPageIsPresent()) {
        amazonProductsPage = amazonProductsPage.goToNextPage();
      } else {
        break;
      }
    } while (true);
  }

  @Test
  public void getDataFromMigthyXUK() {
    AmazonProductsPage amazonProductsPage = new AmazonProductsPage(getDriver());
    amazonProductsPage.openURL(MIGTHY_X_UK_URL);
    if (amazonProductsPage.ifAmazonLogoPresent()) {
      amazonProductsPage.clickToAmazonLogo();
      amazonProductsPage.openURL(MIGTHY_X_UK_URL);
    }
    amazonProductsPage.setLocation("E10 7");
    do {
      amazonProductsPage.scrollToNextPageButton();
      List<Product> products = amazonProductsPage.getProducts();
      products.forEach(System.out::println);
      if (amazonProductsPage.ifNextPageIsPresent()) {
        amazonProductsPage = amazonProductsPage.goToNextPage();
      } else {
        break;
      }
    } while (true);
  }
}
