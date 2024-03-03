package com.gulaev;

import com.gulaev.web.components.ProductItemComponent;
import com.gulaev.web.entity.Product;
import com.gulaev.web.page.MightyXProductPage;
import com.zebrunner.carina.core.AbstractTest;
import java.util.ArrayList;
import java.util.List;
import org.testng.annotations.Test;

public class TestingTest extends AbstractTest {


  @Test
  public void test() {
    MightyXProductPage mightyXProductPage = new MightyXProductPage(getDriver());
    mightyXProductPage.open();
    if (mightyXProductPage.ifAmazonLogoPresent()) {
    mightyXProductPage.clickToAmazonLogo();
    mightyXProductPage.open();
    }
    mightyXProductPage.setLocationUSA();
    List<Product> products = mightyXProductPage.getProducts();
    List<Product> productList = new ArrayList<>();
    productList.forEach(System.out::println);
  }

}
