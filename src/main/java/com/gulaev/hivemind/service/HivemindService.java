package com.gulaev.hivemind.service;

import com.gulaev.amazon.entity.AmazonProduct;
import com.gulaev.amazon.entity.SheetsLink;
import com.gulaev.amazon.service.AmazonProductService;
import com.gulaev.amazon.service.SheetsLinkService;
import com.gulaev.hivemind.entity.HivemindItem;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class HivemindService {

  private AmazonProductService productService;
  private SheetsLinkService sheetsLinkService;

  public HivemindService() {
    this.productService = new AmazonProductService();
    this.sheetsLinkService = new SheetsLinkService();
  }


  public void updateUnitsTotal(List<HivemindItem> hivemindItems) {
    List<AmazonProduct> amazonProducts = productService.getAmazonProductByCurrentDate();
    for (AmazonProduct amazonProduct : amazonProducts) {
      for (HivemindItem hivemindProduct : hivemindItems) {
        if (amazonProduct.getAsin().equals(hivemindProduct.getAsin())) {
          if ((amazonProduct.getShopName().equals("Migthy-X US")
              && hivemindProduct.getMarketplaceDomain().equals("Amazon.com"))
              || (amazonProduct.getShopName().equals("Migthy-X UK")
              && hivemindProduct.getMarketplaceDomain().equals("Amazon.co.uk"))) {
            amazonProduct.setUnitsTotal(hivemindProduct.getUnitsTotal());
          }
        }
      }
    }
    productService.updateUnitsTotal(amazonProducts);
  }


  private void loadHiveMindItems(List<HivemindItem> hivemindItems) {
    productService.deleteByCurrentDate();
    List<AmazonProduct> products = new ArrayList<>();
    for (HivemindItem item: hivemindItems) {
      if (!productService.existByCurrentDateAndAsin(item.getAsin())) {
        AmazonProduct product = new AmazonProduct();
        product.setUnitsTotal(item.getUnitsTotal());
        product.setShopName(item.getMarketplaceDomain());
        product.setUploadedOn(new Date());
        product.setAsin(item.getAsin());
        product.setPrice(item.getPrice());
        product.setShopTitle(item.getShopTitle());
        products.add(product);
      }
    }
    products.forEach(productService::createProduct);
//    productService.addAmazonProductsIfNotExist(products);
  }

  public void checkingReconciliationAndLoad(List<HivemindItem> productItems) {
    List<HivemindItem> checkingItems = new ArrayList<>();
    List<SheetsLink> sheetLinks = sheetsLinkService.getAll();
    List<HivemindItem> usItems = productItems.stream()
        .filter(i -> i.getMarketplaceDomain().equals("Amazon.com")).toList();
    List<HivemindItem> ukItems = productItems.stream()
        .filter(i -> i.getMarketplaceDomain().equals("Amazon.co.uk")).toList();
    List<SheetsLink> sheetsLinksUK = sheetLinks.stream().filter(i -> i.getShopTitle()
        .equals("Mighty-X UK")).toList();
    List<SheetsLink> sheetsLinksUS = sheetLinks.stream().filter(i -> 
        i.getShopTitle().equals("Mighty-X US") || i.getShopTitle().equals("ZOROM'S")
        || i.getShopTitle().equals("Kivals")).toList();

    for (SheetsLink link: sheetsLinksUS) {
      for (HivemindItem item : usItems) {
        if (item.getAsin().equals(link.getAsin()) && !checkingItems.contains(item)) {
          item.setShopTitle(link.getShopTitle());
          checkingItems.add(item);
        }
      }
    }
    Set<String> addedAsins = new HashSet<>(); // Track added ASINs to avoid duplicates

    for (SheetsLink link : sheetsLinksUK) {
      for (HivemindItem item : ukItems) {
        // Check if the item has been added already
        if (!addedAsins.contains(item.getAsin()) && item.getAsin().equals(link.getAsin())) {
          item.setShopTitle(link.getShopTitle());
          checkingItems.add(item);
          addedAsins.add(item.getAsin()); // Mark this ASIN as added
          // If you only want to add one item per ASIN, you can break here
          // break; // Uncomment this if each ASIN should only match once
        }
      }
    }
    loadHiveMindItems(checkingItems);
  }

  private HivemindItem mapItem(SheetsLink sheetsLink) {
    HivemindItem hivemindItem = new HivemindItem();
    String shopTitle = sheetsLink.getShopTitle();
    if (shopTitle.equals("Mighty-X US") || shopTitle.equals("ZOROM'S") || shopTitle.equals("Kivals")) {
      hivemindItem.setMarketplaceDomain("Amazon.com");
    } else {
      hivemindItem.setMarketplaceDomain("Amazon.co.uk");
    }
    hivemindItem.setShopTitle(shopTitle);
    hivemindItem.setAsin(sheetsLink.getAsin());
    hivemindItem.setPrice("Not found in Hivemind");
    hivemindItem.setUnitsTotal("Not found in Hivemind");
    return hivemindItem;
  }
}
