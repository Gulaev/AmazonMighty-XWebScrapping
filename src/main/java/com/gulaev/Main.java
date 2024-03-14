package com.gulaev;

import java.util.ArrayList;
import java.util.List;
import org.testng.TestNG;

public class Main {

  public static void main(String[] args) {
    TestNG testng = new TestNG();

    List<String> suites = new ArrayList<>();
    suites.add("src/test/resources/amazon-suite.xml");
    testng.setTestSuites(suites);
    testng.run();
  }
}
