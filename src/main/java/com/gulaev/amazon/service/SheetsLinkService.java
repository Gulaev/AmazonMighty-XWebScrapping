package com.gulaev.amazon.service;

import com.gulaev.amazon.dao.implementation.SheetsLinkRepositoryImpl;
import com.gulaev.amazon.dao.repository.SheetsLinkRepository;
import com.gulaev.amazon.entity.SheetsLink;
import java.util.List;
import java.util.Optional;

public class SheetsLinkService {

  private SheetsLinkRepository repository;

  public SheetsLinkService() {
    this.repository = new SheetsLinkRepositoryImpl();
  }

  public String getSheetsLinkByAsinAndShopName(String asin, String shopTitle) {
    Optional<SheetsLink> link = repository.getLinkByAsinAndShopTitle(asin, shopTitle);
    if (link.isPresent()) {
      return link.get().getSheetLink();
    } else {
      System.out.println("Not link found");
      return "";
    }
  }  public SheetsLink getLinkByAsinAndShopTitle(String asin, String shopTitle) {
    Optional<SheetsLink> link = repository.getLinkByAsinAndShopTitle(asin, shopTitle);
    if (link.isPresent()) {
      return link.get();
    } else {
      System.out.println("Not link found");
      return new SheetsLink();
    }
  }

  public List<SheetsLink> getAll() {
    return repository.getAll();
  }

}
