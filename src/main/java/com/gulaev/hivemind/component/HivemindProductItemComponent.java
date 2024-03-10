package com.gulaev.hivemind.component;

import com.gulaev.hivemind.entity.HivemindItem;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class HivemindProductItemComponent extends AbstractUIObject {

  @FindBy(xpath = ".//div[@class='product-wrap']//a[not(contains(@href, '#'))]//span")
  private ExtendedWebElement asin;

//  @FindBy(xpath = ".//div[@class='product-wrap']//br/following-sibling::text()[contains(., 'Amazon')][1]/preceding-sibling::br[1]")
//  private ExtendedWebElement marketplaceDomain;

  @FindBy(xpath = ".//div[contains(@class, 'statistic') and .//span[contains(@class, 'badge badge-pill mt-2 badge-secondary')]]/span[1]")
  private ExtendedWebElement unitsTotal;

  @FindBy(xpath = ".//div[@class='product-wrap']")
  private ExtendedWebElement productWrap;

  public HivemindProductItemComponent(WebDriver driver, SearchContext searchContext) {
    super(driver, searchContext);
  }

  public HivemindItem mapItem() {
    HivemindItem item = new HivemindItem();
    item.setAsin(asin.getText());
    item.setUnitsTotal(unitsTotal.getText());
    String productWrapContent = productWrap.getAttribute("textContent"); // or "innerHTML" based on what works best
    String marketplaceDomain = extractMarketplaceDomain(productWrapContent);
    item.setMarketplaceDomain(marketplaceDomain);
    return item;
  }

  private String extractMarketplaceDomain(String productWrapContent) {
    if (productWrapContent.contains("Amazon")) {
      int startIndex = productWrapContent.indexOf("Amazon");
      int endIndex = productWrapContent.indexOf("−", startIndex);
      if (endIndex == -1) {
        endIndex = productWrapContent.length();
      }
      return productWrapContent.substring(startIndex, endIndex).trim();
    }
    return "";
  }

}
