package com.gulaev.hivemind.entity;

import lombok.Data;

@Data
public class HivemindItem {

  private String asin;
  private String marketplaceDomain;
  private String unitsTotal;
  private String price;
}
