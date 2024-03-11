package com.gulaev.amazon.dao.implementation;

import com.gulaev.amazon.dao.config.Config;
import com.gulaev.amazon.dao.repository.AmazonProductRepository;
import com.gulaev.amazon.entity.AmazonProduct;
import java.util.List;
import org.apache.ibatis.session.SqlSession;

public class AmazonProductRepositoryImpl implements AmazonProductRepository {

  @Override
  public void createProduct(AmazonProduct amazonProduct) {
    try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)){
      AmazonProductRepository mapper = sqlSession.getMapper(AmazonProductRepository.class);
      mapper.createProduct(amazonProduct);
    }
  }

  @Override
  public List<AmazonProduct> getByCurrentDate() {
    try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
      AmazonProductRepository mapper = sqlSession.getMapper(AmazonProductRepository.class);
      return mapper.getByCurrentDate();
    }
  }

  @Override
  public void deleteByCurrentDateAndShopName(String shopName) {
    try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
      AmazonProductRepository mapper = sqlSession.getMapper(AmazonProductRepository.class);
      mapper.deleteByCurrentDateAndShopName(shopName);
    }
  }

  @Override
  public void insertProducts(List<AmazonProduct> amazonProducts) {
    try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
      AmazonProductRepository mapper = sqlSession.getMapper(AmazonProductRepository.class);
      mapper.insertProducts(amazonProducts);
    }
  }

  @Override
  public void updateUnitsTotalByIdAndAsin(AmazonProduct product) {
    try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
      AmazonProductRepository mapper = sqlSession.getMapper(AmazonProductRepository.class);
      mapper.updateUnitsTotalByIdAndAsin(product);
    }
  }

  @Override
  public boolean existByCurrentDateAndAsin(String asin) {
    try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
      AmazonProductRepository mapper = sqlSession.getMapper(AmazonProductRepository.class);
      return mapper.existByCurrentDateAndAsin(asin);
    }
  }

  @Override
  public void deleteByCurrentDateAndIfItemNameIsEmpty() {
    try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
      AmazonProductRepository mapper = sqlSession.getMapper(AmazonProductRepository.class);
      mapper.deleteByCurrentDateAndIfItemNameIsEmpty();
    }
  }
}
