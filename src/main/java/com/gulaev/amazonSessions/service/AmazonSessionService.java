package com.gulaev.amazonSessions.service;

import com.gulaev.amazon.dao.implementation.AmazonProductRepositoryImpl;
import com.gulaev.amazon.dao.repository.AmazonProductRepository;
import com.gulaev.amazon.entity.AmazonProduct;
import com.gulaev.amazonSessions.entity.Report;
import com.gulaev.amazonSessions.entity.SalesAndTrafficByAsin;
import com.zebrunner.carina.utils.R;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AmazonSessionService {

  private AmazonProductRepository productRepository;
  private String marketplaceIdUK;
  private String clientIdUKUS;
  private String endpointUK;
  private String endpointUS;
  private String refreshTokenUK;
  private String refreshTokenUS;
  private String marketplaceIdUS;
  private String clientSecretUKUS;

  public AmazonSessionService() {
    this.productRepository = new AmazonProductRepositoryImpl();
    this.marketplaceIdUK = R.TESTDATA.get("marketplaceIdUK");
    this.marketplaceIdUS = R.TESTDATA.get("marketplaceIdUS");
    this.clientIdUKUS = R.TESTDATA.get("clientIdUKUS");
    this.endpointUK = R.TESTDATA.get("endpointUK");
    this.endpointUS = R.TESTDATA.get("endpointUS");
    this.refreshTokenUK = R.TESTDATA.get("refreshTokenUK");
    this.refreshTokenUS = R.TESTDATA.get("refreshTokenUS");
    this.clientSecretUKUS = R.TESTDATA.get("clientSecretUKUS");
  }

  public void getSessionForUK() {
    AmazonRequestService requestService = new AmazonRequestService(clientIdUKUS, clientSecretUKUS,
        refreshTokenUK, endpointUK, marketplaceIdUK);
    Report report = requestService.loadDataFromMarketplace();
    String dateString = report.getReportSpecification().getDataStartTime();
    List<SalesAndTrafficByAsin> salesAndTrafficByAsins = report.getSalesAndTrafficByAsin();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    Date date = new Date();
    try {
      Calendar calendar = Calendar.getInstance();
      date = formatter.parse(dateString);
      calendar.setTime(date);
      calendar.add(Calendar.DATE, -1);
      date = calendar.getTime();
      System.out.println("Date represented is: " + date);
    } catch (Exception e) {
      System.out.println("Error parsing date: " + e.getMessage());
    }
    List<AmazonProduct> productByDateAndShopTitle = productRepository.getProductByDateAndShopTitle(
        date, "Mighty-X UK");
    productByDateAndShopTitle.forEach(System.out::println);

    for (SalesAndTrafficByAsin trafficByAsin : salesAndTrafficByAsins) {
      for (AmazonProduct product : productByDateAndShopTitle) {
        if (trafficByAsin.getChildAsin().equals(product.getAsin())) {
          product.setSessions(String.valueOf(trafficByAsin.getTrafficByAsin().getSessions()));
          productRepository.updateProductById(product);
        }
      }
    }
  }

  public void getSessionForUS() {
    AmazonRequestService requestService = new AmazonRequestService(clientIdUKUS, clientSecretUKUS,
        refreshTokenUS, endpointUS, marketplaceIdUS);
    Report report = requestService.loadDataFromMarketplace();
    String dateString = report.getReportSpecification().getDataStartTime();
    List<SalesAndTrafficByAsin> salesAndTrafficByAsins = report.getSalesAndTrafficByAsin();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    Date date = new Date();
    try {
      Calendar calendar = Calendar.getInstance();
      date = formatter.parse(dateString);
      calendar.setTime(date);
      calendar.add(Calendar.DATE, -1);
      date = calendar.getTime();
      System.out.println("Date represented is: " + date);
    } catch (Exception e) {
      System.out.println("Error parsing date: " + e.getMessage());
    }


    List<AmazonProduct> productByDateAndShopTitle = productRepository.getProductByDateAndShopTitle(
        date, "Mighty-X US");
//    System.out.println(salesAndTrafficByAsin.toArray().length);

    for (SalesAndTrafficByAsin trafficByAsin : salesAndTrafficByAsins) {
      for (AmazonProduct product : productByDateAndShopTitle) {
        if (trafficByAsin.getChildAsin().equals(product.getAsin())) {
          product.setSessions(String.valueOf(trafficByAsin.getTrafficByAsin().getSessions()));
          productRepository.updateProductById(product);
        }
      }
    }
  }
}
