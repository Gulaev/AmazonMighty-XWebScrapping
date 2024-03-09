package com.gulaev.amazon.dao.implementation;

import com.gulaev.amazon.dao.config.Config;
import com.gulaev.amazon.dao.repository.ProductRepository;
import com.gulaev.amazon.entity.Product;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.session.SqlSession;

public class ProductRepositoryImpl implements ProductRepository {

  @Override
  public void createProduct(Product product) {
    try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)){
      ProductRepository mapper = sqlSession.getMapper(ProductRepository.class);
      mapper.createProduct(product);
    }
  }

  @Override
  public List<Product> getByCurrentDate() {
    try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
      ProductRepository mapper = sqlSession.getMapper(ProductRepository.class);
      return mapper.getByCurrentDate();
    }
  }

  @Override
  public void deleteByCurrentDate() {
    try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
      ProductRepository mapper = sqlSession.getMapper(ProductRepository.class);
      mapper.deleteByCurrentDate();
    }
  }

  @Override
  public void insertProducts(List<Product> products) {
    try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
      ProductRepository mapper = sqlSession.getMapper(ProductRepository.class);
      mapper.insertProducts(products);
    }
  }
}
