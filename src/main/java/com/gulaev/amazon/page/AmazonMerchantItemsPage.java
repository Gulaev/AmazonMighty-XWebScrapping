package com.gulaev.amazon.page;


import com.gulaev.amazon.components.ProductItemComponent;
import com.gulaev.amazon.entity.AmazonProduct;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;


public class AmazonMerchantItemsPage extends AmazonHomePage {


  @FindBy(xpath = "//div[contains(@class,'s-result-item') and @data-uuid]")
  private List<ProductItemComponent> products;


  @FindBy(xpath = "//a[contains(@aria-label, 'Go to next page')]")
  private ExtendedWebElement goToNextPage;

  public AmazonMerchantItemsPage(WebDriver driver) {
    super(driver);
  }


  public List<AmazonProduct> getProductsAndSetShop(String shopName) {
    List<AmazonProduct> amazonProductList = new ArrayList<>();
    for (ProductItemComponent product: products) {
      amazonProductList.add(product.mapProduct(shopName));
    }
    return amazonProductList;
  }

  public boolean ifNextPageIsPresent() {
    return goToNextPage.isElementPresent();
  }

  public AmazonMerchantItemsPage goToNextPage() {
    goToNextPage.click();
    return new AmazonMerchantItemsPage(getDriver());
  }

  public void scrollToNextPageButton() {
    if (goToNextPage.isElementPresent()) {
      ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
          goToNextPage.getElement());
    }
  }


}
