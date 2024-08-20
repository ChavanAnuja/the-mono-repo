package login;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import dashboard.Dashboard;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class LoginController {

  @FXML private Label label;

  @FXML private TextField mobileNumber;

  @FXML private Button next;

  @FXML private PasswordField password;

  @FXML private ImageView sublogoImage;

  @FXML private ImageView sublogoblackImage;

  @FXML
  void next(ActionEvent event) throws IOException {

//	  if (mobileNumber.getText().isEmpty() || 
//				password.getText().isEmpty()) {
//					
//				Alert alert = new Alert(Alert.AlertType.ERROR);
//				alert.setTitle("Error");
//				alert.setHeaderText(null);
//				alert.setContentText("Please fill in all the data in all fields.");
//				alert.showAndWait();
//				return;
//			}
//			
//			
//			// The message that is going to be sent to the server
//			// using the POST request
//			final String messageContent = "{\n" + "\"mobileNumber\"" + ":\"" + mobileNumber.getText() + "\", \r\n"
//					+ "\"passcode\"" + ":\"" + password.getText() + "\" \r\n" +"\n}";
//					
//					// Printing the message
//					System.out.println(messageContent);
//
//		 			// URL of the API or Server
//					String url = "http://localhost:8081/api/v1/directory/validate";
//					URL urlObj = new URL(url);
//					HttpURLConnection postCon = (HttpURLConnection) urlObj.openConnection();
//					postCon.setRequestMethod("POST");
//					postCon.setRequestProperty("userId", "abcdef");
//					
//					// Setting the message content type as JSON
//					postCon.setRequestProperty("Content-Type", "application/json");
//					postCon.setDoOutput(true);
//					
//					// for writing the message content to the server
//					OutputStream osObj = postCon.getOutputStream();
//					osObj.write(messageContent.getBytes());
//					
//					// closing the output stream
//					osObj.flush();
//					osObj.close();
//					int respCode = postCon.getResponseCode();
//					System.out.println("Response from the server is: \n");
//					System.out.println("The POST Request Response Code :  " + respCode);
//					System.out.println("The POST Request Response Message : " + postCon.getResponseMessage());
//					if (respCode == HttpURLConnection.HTTP_CREATED) {
//						
//						InputStreamReader irObj = new InputStreamReader(postCon.getInputStream());
//						BufferedReader br = new BufferedReader(irObj);
//						String input = null;
//						StringBuffer sb = new StringBuffer();
//						while ((input = br.readLine()) != null) {
//							sb.append(input);
//						}
//						br.close();
//						postCon.disconnect();
//						// printing the response
//						
//						System.out.println(sb.toString());
	//					new Dashboard().Show();
				//	}
//						else {
//
//							Alert alert = new Alert(Alert.AlertType.ERROR);
//							alert.setTitle("Error");
//							alert.setHeaderText(null);
//							alert.setContentText("Invalid Mobile Number or Password");
//							alert.showAndWait();
//						}
  
  if (mobileNumber.getText().isEmpty() || 
			password.getText().isEmpty()) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Error");
      alert.setHeaderText(null);
      alert.setContentText("Please fill in all the data in all fields.");
      alert.showAndWait();
      return;
  }

  final String messageContent = "{\n" +
          "\"mobileNumber\": \"" + mobileNumber.getText() + "\",\n" +
          "\"password\": \"" + password.getText() + "\"\n}";

  System.out.println(messageContent);

  String url = "http://localhost:8081/api/v1/directory/validate";
  URL urlObj = new URL(url);
  HttpURLConnection postCon = (HttpURLConnection) urlObj.openConnection();
  postCon.setRequestMethod("POST");
  postCon.setRequestProperty("Content-Type", "application/json");
  postCon.setDoOutput(true);

  try (OutputStream osObj = postCon.getOutputStream()) {
      osObj.write(messageContent.getBytes(StandardCharsets.UTF_8));
  }

  int respCode = postCon.getResponseCode();
  System.out.println("Response from the server is: ");
  System.out.println("The POST Request Response Code : " + respCode);
  System.out.println("The POST Request Response Message : " + postCon.getResponseMessage());

  if (respCode == HttpURLConnection.HTTP_OK) {
      try (InputStreamReader irObj = new InputStreamReader(postCon.getInputStream());
           BufferedReader br = new BufferedReader(irObj)) {
          String input;
          StringBuilder sb = new StringBuilder();
          while ((input = br.readLine()) != null) {
              sb.append(input);
          }
          String responseBody = sb.toString();
          System.out.println("Response Body: " + responseBody);
          
          // Assuming the response body is a JSON and contains status and message
          if (responseBody.toLowerCase().contains("\"status\":\"success\"")) {
              new Dashboard().Show();
          } else {
              Alert alert = new Alert(Alert.AlertType.ERROR);
              alert.setTitle("Error");
              alert.setHeaderText(null);
              alert.setContentText("Invalid Mobile Number or Password");
              alert.showAndWait();
          }
      }
  } else {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Error");
      alert.setHeaderText(null);
      alert.setContentText("Invalid Mobile Number or Password");
      alert.showAndWait();
  }

  postCon.disconnect();
}


 
}
