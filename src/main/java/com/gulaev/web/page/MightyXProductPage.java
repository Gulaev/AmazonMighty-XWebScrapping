package com.gulaev.web.page;

import com.gulaev.web.components.HeaderComponent;
import com.gulaev.web.components.PopUpLocationComponent;
import com.gulaev.web.components.ProductItemComponent;
import com.gulaev.web.entity.Product;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MightyXProductPage extends AbstractPage {

  @FindBy(xpath = "//div[@id='a-popover-content-2']")
  private PopUpLocationComponent popUpLocation;

  @FindBy(xpath = "//img[@alt='Amazon.com']")
  private ExtendedWebElement amazonLogo;

  @FindBy(xpath = "//div[contains(@class,'s-result-item') and @data-uuid]")
  private List<ProductItemComponent> products;

  @FindBy(xpath = "//header[@id='navbar-main']")
  private HeaderComponent header;

  public MightyXProductPage(WebDriver driver) {
    super(driver);
  }

  public void clickToAmazonLogo() {
    amazonLogo.click();
  }

  public void setLocationUSA() {
    header.clickToChoiceLocationButton();
    waitUntil(ExpectedConditions.visibilityOf(popUpLocation.getRootExtendedElement()), 10);
    popUpLocation.setUSALocation("97015");
  }

  public List<Product> getProducts() {
    return products.stream().map(ProductItemComponent::mapProductTitleAndRate).toList();
  }

  public boolean ifAmazonLogoPresent() {
    return amazonLogo.isElementPresent();
  }
}
