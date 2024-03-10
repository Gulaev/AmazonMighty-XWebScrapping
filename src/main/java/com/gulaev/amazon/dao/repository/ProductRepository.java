package com.gulaev.amazon.dao.repository;

import com.gulaev.amazon.entity.Product;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProductRepository {

  void createProduct(@Param("product") Product product);

  List<Product> getByCurrentDate();

  void deleteByCurrentDateAndShopName(@Param("shopName") String shopName);

  void insertProducts(@Param("list")List<Product> products);
}
