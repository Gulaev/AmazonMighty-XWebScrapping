package com.gulaev.amazon.components;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class HeaderComponent extends AbstractUIObject {

  @FindBy(id = "nav-global-location-popover-link")
  private ExtendedWebElement choiceLocationDeliveryButton;

  public HeaderComponent(WebDriver driver, SearchContext searchContext) {
    super(driver, searchContext);
    setUiLoadedMarker(choiceLocationDeliveryButton);
  }

  public void clickToChoiceLocationButton() {
    choiceLocationDeliveryButton.click();
  }
}
