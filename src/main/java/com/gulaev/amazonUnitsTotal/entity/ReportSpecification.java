package com.gulaev.amazonUnitsTotal.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReportSpecification {

  @JsonProperty("reportType")
  public String reportType;
  @JsonProperty("marketplaceIds")
  public List<String> marketplaceIds;
  @JsonProperty("dataEndTime")
  public String dataEndTime;
  @JsonProperty("dataStartTime")
  public String dataStartTime;
}