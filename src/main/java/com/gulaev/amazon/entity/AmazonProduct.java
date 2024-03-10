package com.gulaev.amazon.entity;

import java.util.Date;
import lombok.Data;

@Data
public class AmazonProduct {

  private long id;
  private String starRating;
  private String title;
  private String rateCount;
  private String asin;
  private String shopName;
  private Date uploadedOn;
  private String unitsTotal;
}