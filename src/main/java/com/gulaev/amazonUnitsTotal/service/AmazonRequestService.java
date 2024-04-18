package com.gulaev.amazonUnitsTotal.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gulaev.amazonUnitsTotal.entity.Report;
import com.zebrunner.carina.utils.R;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;
import java.util.zip.GZIPInputStream;
import org.json.JSONObject;

public class AmazonRequestService {
  private String clientId;
  private String clientSecret;
  private String refreshToken;
  private String endpoint;
  private String marketplaceId;
  private HttpClient client;

  public AmazonRequestService(String clientId, String clientSecret, String refreshToken,
      String endpoint, String marketplaceId) {
    this.client = HttpClient.newHttpClient();  // Initialize HttpClient
    this.clientId = clientId;
    this.clientSecret = clientSecret;
    this.refreshToken = refreshToken;
    this.endpoint = endpoint;
    this.marketplaceId = marketplaceId;
  }

  private String getAccessToken() throws IOException, InterruptedException {
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("https://api.amazon.com/auth/o2/token"))
        .header("Content-Type", "application/x-www-form-urlencoded")
        .POST(HttpRequest.BodyPublishers.ofString(
            "grant_type=refresh_token&refresh_token=" + refreshToken +
                "&client_id=" + clientId + "&client_secret=" + clientSecret))
        .build();

    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    JSONObject jsonResponse = new JSONObject(response.body());
    return jsonResponse.getString("access_token");
  }

  private JSONObject createReportRequest(String marketplaceId, String accessToken, String dataStartTime, String dataEndTime) throws IOException, InterruptedException {
    String requestBody = new JSONObject()
        .put("reportType", "GET_SALES_AND_TRAFFIC_REPORT")
        .put("marketplaceIds", new String[]{marketplaceId})
        .put("dataStartTime", dataStartTime)
        .put("dataEndTime", dataEndTime)
        .put("reportOptions", new JSONObject().put("dateGranularity", "DAY").put("asinGranularity", "SKU"))
        .toString();

    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(endpoint + "/reports/2021-06-30/reports"))
        .header("Content-Type", "application/json")
        .header("x-amz-access-token", accessToken)
        .POST(HttpRequest.BodyPublishers.ofString(requestBody))
        .build();

    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    return new JSONObject(response.body());
  }

  private JSONObject getReportStatus(String accessToken, String reportId) throws IOException, InterruptedException {
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(endpoint + "/reports/2021-06-30/reports/" + reportId))
        .header("x-amz-access-token", accessToken)
        .GET()
        .build();

    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    return new JSONObject(response.body());
  }

  private JSONObject downloadReport(String accessToken, String documentId) throws IOException, InterruptedException {
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(endpoint + "/reports/2021-06-30/documents/" + documentId))
        .header("x-amz-access-token", accessToken)
        .GET()
        .build();

    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    return new JSONObject(response.body());
  }

  private JSONObject downloadReportContent(String reportUrl) throws IOException {
    URL url = new URL(reportUrl);
    try (InputStream in = new GZIPInputStream(url.openStream());
        BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8))) {
      String result = reader.lines().collect(Collectors.joining("\n"));
      return new JSONObject(result);
    }
  }

  public Report loadDataFromMarketplace() {
    try {
      String accessToken = getAccessToken();
      LocalDate dayBeforeYesterday = LocalDate.now().minusDays(2);
      ZonedDateTime startOfTheDay = dayBeforeYesterday.atStartOfDay(ZoneId.of("UTC"));
      ZonedDateTime endOfTheDay = dayBeforeYesterday.atTime(23, 59, 59).atZone(ZoneId.of("UTC"));
      DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT;
      String startTime = formatter.format(startOfTheDay.toInstant());
      String endTime = formatter.format(endOfTheDay.toInstant());


      // Create report request
      JSONObject reportResponse = createReportRequest(marketplaceId, accessToken,
          startTime,
          endTime);

      System.out.println("Report Requested: " + reportResponse.toString());
      String reportId = reportResponse.getString("reportId");

      // Check report status periodically
      boolean isReportReady = false;
      JSONObject reportStatus = null;
      while (!isReportReady) {
        reportStatus = getReportStatus(accessToken, reportId);
        String status = reportStatus.getString("processingStatus");
        System.out.println("Report Status: " + status);
        if ("DONE".equals(status)) {
          isReportReady = true;
        } else {
          // Sleep for some time before checking again
          Thread.sleep(60000); // Sleep for 60 seconds
        }
      }

      // Report is ready, download it
      if (reportStatus != null && isReportReady) {
        String documentId = reportStatus.getString("reportDocumentId");
        JSONObject reportDocumentDetails = downloadReport(accessToken, documentId);
        String reportUrl = reportDocumentDetails.getString("url");
        System.out.println("Report Download URL: " + reportUrl);
        System.out.println(reportDocumentDetails.toString());
        JSONObject result = downloadReportContent(reportUrl);
        System.out.println(result.toString());
        ObjectMapper mapper = new ObjectMapper();
        Report report = mapper.readValue(result.toString(), Report.class);
        report.toString();
        return report;
      }
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
    }
    return new Report();
  }
}
