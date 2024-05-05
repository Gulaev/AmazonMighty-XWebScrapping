package com.gulaev;

import java.util.ArrayList;
import java.util.List;
import org.testng.TestNG;

public class Main {

  public static void main(String[] args) {
    HiveMindScrapData start = new HiveMindScrapData();
    start.scrapDataHivemind();
    start.scrapDataFromAmazonUS();
    start.scrapDataFromAmazonUK();
    start.scrapSessions();
  }
}
