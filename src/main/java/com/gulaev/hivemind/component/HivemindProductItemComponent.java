package com.gulaev.hivemind.component;

import com.gulaev.hivemind.entity.HivemindItem;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.NoSuchElementException;

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
    String currentUnitsTotal = unitsTotal.isPresent() ? unitsTotal.getText() : "0";
    item.setUnitsTotal(currentUnitsTotal);
    String productWrapContent = productWrap.getAttribute("textContent");
    String marketplaceDomain = extractMarketplaceDomain(productWrapContent);
    item.setMarketplaceDomain(marketplaceDomain);
    item.setPrice(extractPrice(productWrapContent));
    return item;
  }

  private String extractMarketplaceDomain(String productWrapContent) {
    if (productWrapContent.contains("Amazon")) {
      int startIndex = productWrapContent.indexOf("Amazon");
      int endIndex = productWrapContent.length();

      if (productWrapContent.contains("$")) {
        endIndex = productWrapContent.indexOf("$");
      } else if (productWrapContent.contains("£")) {
        endIndex = productWrapContent.indexOf("£");
      }
      endIndex = endIndex-3;
      return productWrapContent.substring(startIndex, endIndex).trim();
    }
    return "";
  }

  private String extractPrice(String productWrapContent) {
    int startIndex = 0;
    int endIndex = productWrapContent.length();

    if (productWrapContent.contains("$")) {
      startIndex = productWrapContent.indexOf("$");
    } else if (productWrapContent.contains("£")) {
      startIndex = productWrapContent.indexOf("£");
    }

    endIndex = productWrapContent.indexOf(" ", startIndex);
    if (endIndex == -1) {
      endIndex = productWrapContent.length();
    }

    return productWrapContent.substring(startIndex, endIndex).trim();
  }
}

