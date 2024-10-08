package common;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RestUtilGenerics {
  private static final ObjectMapper objectMapper = new ObjectMapper();

  public static <T> T sendGetRequest(String url, Class<T> responseType) throws Exception {
    HttpClient client = HttpClient.newHttpClient(); // postman
    HttpRequest request =
        HttpRequest.newBuilder()
            .uri(URI.create(url))
            .header("Content-Type", "application/json")
            .GET()
            .build(); // object

    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

    T bookResponse = objectMapper.readValue(response.body(), responseType);
    return bookResponse;
  }

  public static <T> T sendPostRequest(String url, Class<T> responseType, Object bookRequest)
      throws Exception {
    String jsonRequestAsString = objectMapper.writeValueAsString(bookRequest);

    HttpClient client = HttpClient.newHttpClient();

    HttpRequest request =
        HttpRequest.newBuilder()
            .uri(URI.create(url))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(jsonRequestAsString))
            .build();

    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

    String strResponse = response.body();
    System.out.println(strResponse);

    T userResponse = objectMapper.readValue(response.body(), responseType);

    return userResponse;
  }

  public static <T> T sendPutRequest(String url, Class<T> responseType, Object requestObject)
      throws IOException, InterruptedException {
    String jsonRequestAsString = objectMapper.writeValueAsString(requestObject);

    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request =
        HttpRequest.newBuilder()
            .uri(URI.create(url))
            .header("Content-Type", "application/json")
            .PUT(HttpRequest.BodyPublishers.ofString(jsonRequestAsString))
            .build();

    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

    T userResponse = objectMapper.readValue(response.body(), responseType);
    return userResponse;
  }
}
