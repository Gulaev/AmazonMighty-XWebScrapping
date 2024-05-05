package com.gulaev.amazon.entity;

import java.util.Date;
import lombok.Data;

@Data
public class AmazonProduct {

  private long id;
  private String starRating;
  private String title;
  private String rateCount;
  private String price;
  private String asin;
  private String shopName;
  private Date uploadedOn;
  private String unitsTotal;
  private String shopTitle;
  private String sheetLink;
  private String sessions;
  private String rank;
  private String bestSellerRank;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getStarRating() {
    return starRating;
  }

  public void setStarRating(String starRating) {
    this.starRating = starRating;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getRateCount() {
    return rateCount;
  }

  public void setRateCount(String rateCount) {
    this.rateCount = rateCount;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public String getAsin() {
    return asin;
  }

  public void setAsin(String asin) {
    this.asin = asin;
  }

  public String getShopName() {
    return shopName;
  }

  public void setShopName(String shopName) {
    this.shopName = shopName;
  }

  public Date getUploadedOn() {
    return uploadedOn;
  }

  public void setUploadedOn(Date uploadedOn) {
    this.uploadedOn = uploadedOn;
  }

  public String getUnitsTotal() {
    return unitsTotal;
  }

  public void setUnitsTotal(String unitsTotal) {
    this.unitsTotal = unitsTotal;
  }

  public String getShopTitle() {
    return shopTitle;
  }

  public void setShopTitle(String shopTitle) {
    this.shopTitle = shopTitle;
  }

  public String getSheetLink() {
    return sheetLink;
  }

  public void setSheetLink(String sheetLink) {
    this.sheetLink = sheetLink;
  }

  public String getSessions() {
    return sessions;
  }

  public void setSessions(String sessions) {
    this.sessions = sessions;
  }

  public String getRank() {
    return rank;
  }

  public void setRank(String rank) {
    this.rank = rank;
  }

  public String getBestSellerRank() {
    return bestSellerRank;
  }

  public void setBestSellerRank(String bestSellerRank) {
    this.bestSellerRank = bestSellerRank;
  }
}
