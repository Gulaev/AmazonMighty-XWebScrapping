package com.gulaev.hivemind.service;

import com.gulaev.amazon.entity.AmazonProduct;
import com.gulaev.amazon.service.AmazonProductService;
import com.gulaev.hivemind.entity.HivemindItem;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HivemindService {

  private AmazonProductService productService;

  public HivemindService() {
    this.productService = new AmazonProductService();
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


  public void loadHiveMindItems(List<HivemindItem> hivemindItems) {
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
        products.add(product);
      }
    }
    productService.addAmazonProductsIfNotExist(products);
  }
}
