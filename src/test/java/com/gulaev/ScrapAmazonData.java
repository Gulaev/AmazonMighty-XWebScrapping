package com.gulaev;

import com.gulaev.amazon.entity.AmazonProduct;
import com.gulaev.amazon.page.AmazonMerchantItemsPage;
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
    AmazonMerchantItemsPage amazonMerchantItemsPage = new AmazonMerchantItemsPage(getDriver());
    amazonMerchantItemsPage.openURL(MIGTHY_X_US_URL);
    if (amazonMerchantItemsPage.ifAmazonLogoPresent()) {
      amazonMerchantItemsPage.clickToAmazonLogo();
      amazonMerchantItemsPage.openURL(MIGTHY_X_US_URL);
    }
    amazonMerchantItemsPage.setLocation("97015");
    List<AmazonProduct> amazonProducts = new ArrayList<>();
    do {
      amazonMerchantItemsPage.scrollToNextPageButton();
      List<AmazonProduct> currentListAmazonProducts = amazonMerchantItemsPage
          .getProductsAndSetShop(shopName);
      amazonProducts.addAll(currentListAmazonProducts);
      amazonProducts.forEach(System.out::println);
      if (amazonMerchantItemsPage.ifNextPageIsPresent()) {
        amazonMerchantItemsPage = amazonMerchantItemsPage.goToNextPage();
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
    AmazonMerchantItemsPage amazonMerchantItemsPage = new AmazonMerchantItemsPage(getDriver());
    amazonMerchantItemsPage.openURL(AMAZON_HOME_PAGE_UK);
    amazonMerchantItemsPage.openURL(MIGTHY_X_UK_URL);
    amazonMerchantItemsPage.openURL(AMAZON_HOME_PAGE_UK);
    amazonMerchantItemsPage.openURL(MIGTHY_X_UK_URL);

    if (amazonMerchantItemsPage.ifAmazonLogoPresent()) {
      amazonMerchantItemsPage.clickToAmazonLogo();
      amazonMerchantItemsPage.openURL(MIGTHY_X_UK_URL);
    }
    amazonMerchantItemsPage.setLocation("NW7 1SW");
    amazonMerchantItemsPage.openURL(MIGTHY_X_UK_URL);
    List<AmazonProduct> amazonProducts = new ArrayList<>();
    do {
      amazonMerchantItemsPage.scrollToNextPageButton();
      List<AmazonProduct> currentPageAmazonProducts = amazonMerchantItemsPage
          .getProductsAndSetShop(shopName);
      amazonProducts.addAll(currentPageAmazonProducts);
      amazonProducts.forEach(System.out::println);
      if (amazonMerchantItemsPage.ifNextPageIsPresent()) {
        amazonMerchantItemsPage = amazonMerchantItemsPage.goToNextPage();
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
    AmazonMerchantItemsPage amazonMerchantItemsPage = new AmazonMerchantItemsPage(getDriver());
    amazonMerchantItemsPage.openURL(KIVALS_US_URL);
    if (amazonMerchantItemsPage.ifAmazonLogoPresent()) {
      amazonMerchantItemsPage.clickToAmazonLogo();
      amazonMerchantItemsPage.openURL(KIVALS_US_URL);
    }
    amazonMerchantItemsPage.setLocation("97015");
    List<AmazonProduct> amazonProducts = new ArrayList<>();
    do {
      amazonMerchantItemsPage.scrollToNextPageButton();
      List<AmazonProduct> currentPageAmazonProducts = amazonMerchantItemsPage
          .getProductsAndSetShop(shopName);
      amazonProducts.addAll(currentPageAmazonProducts);
      amazonProducts.forEach(System.out::println);
      if (amazonMerchantItemsPage.ifNextPageIsPresent()) {
        amazonMerchantItemsPage = amazonMerchantItemsPage.goToNextPage();
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
    AmazonMerchantItemsPage amazonMerchantItemsPage = new AmazonMerchantItemsPage(getDriver());
    amazonMerchantItemsPage.openURL(ZOROMS_US_URL);
    if (amazonMerchantItemsPage.ifAmazonLogoPresent()) {
      amazonMerchantItemsPage.clickToAmazonLogo();
      amazonMerchantItemsPage.openURL(ZOROMS_US_URL);
    }
    amazonMerchantItemsPage.setLocation("97015");
    List<AmazonProduct> amazonProducts = new ArrayList<>();
    do {
      amazonMerchantItemsPage.scrollToNextPageButton();
      List<AmazonProduct> currentPageAmazonProducts = amazonMerchantItemsPage
          .getProductsAndSetShop(shopName);
      amazonProducts.addAll(currentPageAmazonProducts);
      amazonProducts.forEach(System.out::println);
      if (amazonMerchantItemsPage.ifNextPageIsPresent()) {
        amazonMerchantItemsPage = amazonMerchantItemsPage.goToNextPage();
      } else {
        amazonProductService.updateProductByShop(amazonProducts, shopName);
        break;
      }
    } while (true);
  }


}
