package com.gulaev.hiveMind.page;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends AbstractPage {

  @FindBy(id = "email")
  private ExtendedWebElement inputEmailField;

  @FindBy(id = "password")
  private ExtendedWebElement inputPasswordField;

  @FindBy(xpath = "//button[@type='submit']")
  private ExtendedWebElement submitButton;

  public LoginPage(WebDriver driver) {
    super(driver);
  }


}
