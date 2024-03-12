package com.gulaev.amazon.dao.repository;

import com.gulaev.amazon.entity.AmazonProduct;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AmazonProductRepository {

  void createProduct(@Param("amazonProduct") AmazonProduct amazonProduct);

  List<AmazonProduct> getByCurrentDate();

  void deleteByCurrentDateAndShopName(@Param("shopName") String shopName);

  void insertProducts(@Param("list")List<AmazonProduct> amazonProducts);

  void updateUnitsTotalByIdAndAsin(@Param("product") AmazonProduct product);

  void updateProductById(@Param("product") AmazonProduct product);

  boolean existByCurrentDateAndAsin(@Param("asin") String asin);

  void deleteByCurrentDateAndIfItemNameIsEmpty();

  List<AmazonProduct> getProductsWhereTitleNullAndShopNameAndCurrentDate(@Param("shopName") String shopName);
}
