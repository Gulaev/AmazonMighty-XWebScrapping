package com.gulaev.amazon.page;

import com.gulaev.amazon.components.HeaderComponent;
import com.gulaev.amazon.components.PopUpLocationComponent;
import com.gulaev.amazon.components.ProductItemComponent;
import com.gulaev.amazon.entity.Product;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AmazonProductsPage extends AbstractPage {

  @FindBy(xpath = "//div[@id='a-popover-content-2']")
  private PopUpLocationComponent popUpLocation;

  @FindBy(xpath = "//img[@alt='Amazon.com']")
  private ExtendedWebElement amazonLogo;

  @FindBy(xpath = "//div[contains(@class,'s-result-item') and @data-uuid]")
  private List<ProductItemComponent> products;

  @FindBy(xpath = "//header[@id='navbar-main']")
  private HeaderComponent header;

  @FindBy(xpath = "//a[contains(@aria-label, 'Go to next page')]")
  private ExtendedWebElement goToNextPage;

  public AmazonProductsPage(WebDriver driver) {
    super(driver);
  }

  @Override
  public void openURL(String url) {
    super.openURL(url);
  }

  public void clickToAmazonLogo() {
    amazonLogo.click();
  }

  public void setLocation(String zipCode) {
    header.clickToChoiceLocationButton();
    waitUntil(ExpectedConditions.visibilityOf(popUpLocation.getRootExtendedElement()), 10);
    popUpLocation.setUSALocation(zipCode);
  }

  public List<Product> getProducts() {
    return products.stream().map(ProductItemComponent::mapProductTitleAndRate).toList();
  }

  public boolean ifNextPageIsPresent() {
    return goToNextPage.isElementPresent();
  }

  public AmazonProductsPage goToNextPage() {
    goToNextPage.click();
    return new AmazonProductsPage(getDriver());
  }

  public void scrollToNextPageButton() {
    if (goToNextPage.isElementPresent()) {
      ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
          goToNextPage.getElement());
    }
  }

  public boolean ifAmazonLogoPresent() {
    return amazonLogo.isElementPresent();
  }
}
