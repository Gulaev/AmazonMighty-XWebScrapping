package com.gulaev.amazonUnitsTotal.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrafficByAsin {

  @JsonProperty("sessionPercentage")
  public double sessionPercentage;
  @JsonProperty("sessions")
  public int sessions;
  @JsonProperty("pageViews")
  public int pageViews;

}
