package com.gulaev.hivemind.page;

import com.gulaev.hivemind.component.HivemindProductItemComponent;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HivemideHomePage extends AbstractPage {

  @FindBy(xpath = "//div[@class='card-body']//table//tbody//tr")
  private List<HivemindProductItemComponent> productItems;

  @FindBy(xpath = "(//div[@class=\"date-time-picker\"])[1]")
  private ExtendedWebElement choiceDateButton;

  @FindBy(xpath = "(//li[@class=\"page-item\"][.//a[contains(@class, 'page-link') and contains(text(), 'Next')]])[2]")
  private ExtendedWebElement nextPage;

  @FindBy(xpath = "(//div[@class='shortcuts-container'])[1]//span[contains(text(), 'Today')]")
  private ExtendedWebElement todayButton;



  public HivemideHomePage(WebDriver driver) {
    super(driver);
  }

  public List<HivemindProductItemComponent> getProductItems() {
    return productItems;
  }

  public void goToNextPage() {
    nextPage.click();
  }


  public HivemideHomePage choiceTodayData() {
    choiceDateButton.click();
    waitUntil(ExpectedConditions.visibilityOf(todayButton), 10);
    todayButton.click();
    return new HivemideHomePage(getDriver());
  }


  public boolean isNextPagePresent() {
    try {
      WebElement element = driver.findElement(
          By.xpath("(//li[@class=\"page-item\"][.//a[contains(@class, 'page-link') and contains(text(), 'Next')]])[2]"));
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }


}
