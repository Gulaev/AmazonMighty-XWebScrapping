package com.gulaev;

import com.gulaev.hivemind.component.HivemindProductItemComponent;
import com.gulaev.hivemind.entity.HivemindItem;
import com.gulaev.hivemind.page.HivemideHomePage;
import com.gulaev.hivemind.page.HivemindLoginPage;
import com.gulaev.hivemind.service.HivemindService;
import com.zebrunner.carina.core.AbstractTest;
import com.zebrunner.carina.utils.R;
import java.util.ArrayList;
import java.util.List;
import org.testng.annotations.Test;

public class HiveMindScrapData extends AbstractTest {

  private final String HIVE_MIND_URL = R.CONFIG.get("urlHiveMind");
  private final String EMAIL = R.TESTDATA.get("email");
  private final String PASSWORD = R.TESTDATA.get("password");


  @Test
  public void scrapData() {
    HivemindService hivemindService = new HivemindService();
    HivemindLoginPage hivemindLoginPage = new HivemindLoginPage(getDriver());
    hivemindLoginPage.openURL(HIVE_MIND_URL);
    HivemideHomePage homePage = hivemindLoginPage.login(EMAIL, PASSWORD);
    homePage.choiceTodayData();
    List<HivemindItem> productItems = new ArrayList<>();

    do {
      pause(5);
      List<HivemindItem> itemsOnPage = homePage.getProductItems().stream()
          .map(HivemindProductItemComponent::mapItem).toList();
      productItems.addAll(itemsOnPage);

      if (homePage.isNextPagePresent()) {
        homePage.goToNextPage();
        pause(5);
      } else {
        break;
      }
    } while (true);

    productItems.forEach(System.out::println);
    hivemindService.updateUnitsTotal(productItems);
    hivemindService.loadHiveMindItems(productItems);
  }

}
