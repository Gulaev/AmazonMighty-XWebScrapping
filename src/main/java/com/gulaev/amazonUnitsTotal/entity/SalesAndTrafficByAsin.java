package com.gulaev.amazonUnitsTotal.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SalesAndTrafficByAsin {

  @JsonProperty("parentAsin")
  public String parentAsin;
  @JsonProperty("salesByAsin")
  public SalesByAsin salesByAsin;
  @JsonProperty("childAsin")
  public String childAsin;
  @JsonProperty("sku")
  public String sku;
  @JsonProperty("trafficByAsin")
  public TrafficByAsin trafficByAsin;
}
