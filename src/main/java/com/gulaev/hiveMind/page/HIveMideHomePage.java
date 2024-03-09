package com.gulaev.hiveMind.page;

import com.gulaev.hiveMind.component.HiveMindProductItemComponent;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class HIveMideHomePage extends AbstractPage {

  @FindBy()
  private List<HiveMindProductItemComponent> productItems;

  public HIveMideHomePage(WebDriver driver) {
    super(driver);
  }



}
