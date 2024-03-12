package com.gulaev.hivemind.page;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class HivemindLoginPage extends AbstractPage {

  @FindBy(id = "email")
  private ExtendedWebElement inputEmailField;

  @FindBy(id = "password")
  private ExtendedWebElement inputPasswordField;

  @FindBy(xpath = "//button[@type='submit']")
  private ExtendedWebElement submitButton;

  public HivemindLoginPage(WebDriver driver) {
    super(driver);
  }


  public HivemideHomePage login(String email, String password) {
    inputEmailField.type(email);
    inputPasswordField.type(password);
    submitButton.click();
    return new HivemideHomePage(getDriver());
  }


}
