package com.gulaev.amazon.entity;

import lombok.Data;

@Data
public class Product {

  private long id;
  private String starRating;
  private String title;
  private String rateCount;
  private String asin;
}
