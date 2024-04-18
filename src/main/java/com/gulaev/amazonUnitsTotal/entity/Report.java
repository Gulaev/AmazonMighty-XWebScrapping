package com.gulaev.amazonUnitsTotal.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Report {

  @JsonProperty("salesAndTrafficByAsin")
  public List<SalesAndTrafficByAsin> salesAndTrafficByAsin;

  @JsonProperty("reportSpecification")
  public ReportSpecification reportSpecification;


  public ReportSpecification getReportSpecification() {
    return reportSpecification;
  }

  public void setReportSpecification(
      ReportSpecification reportSpecification) {
    this.reportSpecification = reportSpecification;
  }

  public List<SalesAndTrafficByAsin> getSalesAndTrafficByAsin() {
    return salesAndTrafficByAsin;
  }

  public void setSalesAndTrafficByAsin(List<SalesAndTrafficByAsin> salesAndTrafficByAsin) {
    this.salesAndTrafficByAsin = salesAndTrafficByAsin;
  }
}