package com.gulaev.hivemind.entity;

import java.util.Objects;
import lombok.Data;

@Data
public class HivemindItem {

  private String asin;
  private String marketplaceDomain;
  private String unitsTotal;
  private String price;
  private String shopTitle;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    HivemindItem that = (HivemindItem) o;
    return asin.equals(that.asin) && marketplaceDomain.equals(that.marketplaceDomain)
        && shopTitle.equals(that.shopTitle);
  }

  @Override
  public int hashCode() {
    return Objects.hash(asin, marketplaceDomain, shopTitle);
  }
}
