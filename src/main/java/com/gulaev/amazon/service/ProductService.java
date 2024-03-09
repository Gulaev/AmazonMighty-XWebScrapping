package com.gulaev.amazon.service;

import com.gulaev.amazon.dao.implementation.ProductRepositoryImpl;
import com.gulaev.amazon.dao.repository.ProductRepository;
import com.gulaev.amazon.entity.Product;
import java.util.List;

public class ProductService {

  private ProductRepository productRepository;

  public ProductService() {
    this.productRepository = new ProductRepositoryImpl();
  }

  public void updateProduct(List<Product> products) {
      productRepository.deleteByCurrentDate();
      productRepository.insertProducts(products);
  }
}
