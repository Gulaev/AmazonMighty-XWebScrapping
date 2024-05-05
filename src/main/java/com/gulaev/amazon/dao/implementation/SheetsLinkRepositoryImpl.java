package com.gulaev.amazon.dao.implementation;

import com.gulaev.amazon.dao.config.Config;
import com.gulaev.amazon.dao.repository.SheetsLinkRepository;
import com.gulaev.amazon.entity.SheetsLink;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.session.SqlSession;

public class SheetsLinkRepositoryImpl implements SheetsLinkRepository {

  @Override
  public Optional<SheetsLink> getLinkByAsinAndShopTitle(String asin, String shopTitle) {
    try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
      SheetsLinkRepository mapper = sqlSession.getMapper(SheetsLinkRepository.class);
      return mapper.getLinkByAsinAndShopTitle(asin, shopTitle);
    }
  }

  @Override
  public List<SheetsLink> getAll() {
    try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
      SheetsLinkRepository mapper = sqlSession.getMapper(SheetsLinkRepository.class);
      return mapper.getAll();
    }
  }
}
