package com.gulaev.amazon.service;

import com.gulaev.amazon.dao.implementation.AmazonProductRepositoryImpl;
import com.gulaev.amazon.dao.repository.AmazonProductRepository;
import com.gulaev.amazon.entity.AmazonProduct;
import java.util.List;

public class AmazonProductService {

  private AmazonProductRepository amazonProductRepository;

  public AmazonProductService() {
    this.amazonProductRepository = new AmazonProductRepositoryImpl();
  }

  public void updateProductByShop(List<AmazonProduct> amazonProducts, String shopName) {
      amazonProductRepository.deleteByCurrentDateAndShopName(shopName);
      amazonProductRepository.insertProducts(amazonProducts);
  }

  public List<AmazonProduct> getAmazonProductByCurrentDate() {
    return amazonProductRepository.getByCurrentDate();
  }

  public void updateUnitsTotal(List<AmazonProduct> amazonProducts) {
    amazonProducts.forEach(a -> amazonProductRepository.updateUnitsTotalByIdAndAsin(a));
  }

  public boolean existByCurrentDateAndAsin(String asin) {
    return amazonProductRepository.existByCurrentDateAndAsin(asin);
  }

  public void addAmazonProductsIfNotExist(List<AmazonProduct> product) {
    amazonProductRepository.insertProducts(product);
  }
  public void deleteByCurrentDateAndIfItemNameIsEmpty() {
    amazonProductRepository.deleteByCurrentDateAndIfItemNameIsEmpty();
  }

  public List<AmazonProduct> getProductsWhereTitleNullAndShopName(String shopName) {
   return amazonProductRepository.getProductsWhereTitleNullAndShopNameAndCurrentDate(shopName);
  }

  public void updateProduct(AmazonProduct product) {
    amazonProductRepository.updateProductById(product);
  }
}
