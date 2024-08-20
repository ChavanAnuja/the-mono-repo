package add_appointment;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.dnyanyog.dto.request.AppointmentRequest;
import org.dnyanyog.entity.Appointment;
import org.dnynayog.common.ResponseCodes;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import add_case.AddCase;
import appointment_management.AppointmentManagement;
import case_management.CaseManagement;
import common.ApiEndPoints;
import dashboard.Dashboard;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import patient_management.PatientManagement;
import user_management.UserManagement;

public class AddAppointmentController {

  @FXML private Label addAppointment;

  @FXML private Label admin;

  @FXML private Button appointment;

  @FXML private TextField appointmentId;

  @FXML private TextField appointmentTime;

  @FXML private Button cancel;

  @FXML private Button cases;

  @FXML private Button dashboard;

  @FXML
  private TextField examinationDate;
  @FXML private Button logout;

  @FXML private TextField patientId;

  @FXML private TextField patientNameEnglish;

  @FXML private Label message;

  @FXML private Button patients;

  @FXML private Button save;

  @FXML private Button users;

  @FXML
  void Save(ActionEvent event) {

	  String appointmentIdText = appointmentId.getText();
	    String patientNameEnglishText = patientNameEnglish.getText();
	    String patientIdText = patientId.getText();
	    String examinationDateText = examinationDate.getText();
	    String appointmentTimeText = appointmentTime.getText();

	    if (appointmentIdText.isEmpty()
	        || patientNameEnglishText.isEmpty()
	        || patientIdText.isEmpty()
	        || examinationDateText == null
	        || appointmentTimeText.isEmpty()) {
	      message.setText("All fields must be filled.");
	      message.setStyle("-fx-text-fill: red;");
	      return;
	    }

	    try {
	      Long appointmentIdLong = Long.parseLong(appointmentIdText);
	      Long patientIdLong = Long.parseLong(patientIdText);

	      Appointment appointment = new Appointment();
	      appointment.setAppointmentId(appointmentIdLong);
	      appointment.setPatientNameEnglish(patientNameEnglishText);
	      appointment.setPatientId(patientIdLong);
	      //appointment.setExaminationDate(examinationDateText);
	      appointment.setAppointmentTime(appointmentTimeText);
	      appointment.setAppointmentStatus("Active");

	      String url = ApiEndPoints.ADD_APPOINTMENT;

	      // Create HTTP headers
	      HttpHeaders headers = new HttpHeaders();
	      headers.setContentType(MediaType.APPLICATION_JSON);
	      HttpEntity<Appointment> requestEntity = new HttpEntity<>(appointment, headers);

	      // Send POST request
	      RestTemplate restTemplate = new RestTemplate();
	      ResponseEntity<Appointment> response = restTemplate.postForEntity(url, requestEntity, Appointment.class);

	      if (response.getStatusCode().is2xxSuccessful()) {
	        message.setText("Appointment saved successfully.");
	        message.setStyle("-fx-text-fill: green;");
	        clearFields(); // Assuming this method clears input fields

	        Alert alert = new Alert(Alert.AlertType.INFORMATION);
	        alert.setTitle("Appointment Added");
	        alert.setContentText("Appointment added!");
	        alert.setHeaderText("Success!!");
	        alert.show();

	      } else {
	        message.setText("Error saving appointment.");
	        message.setStyle("-fx-text-fill: red;");
	      }
	    } catch (NumberFormatException e) {
	      message.setText("Invalid format for appointment ID or patient ID.");
	      message.setStyle("-fx-text-fill: red;");
	    } catch (Exception e) {
	      message.setText("An error occurred while saving the appointment.");
	      message.setStyle("-fx-text-fill: red;");
	      e.printStackTrace();
	    }
  }


  @FXML
  void clearFields() {
    appointmentId.clear();
    patientNameEnglish.clear();
    patientId.clear();
    examinationDate.clear();
    appointmentTime.clear();
  }

  @FXML
  void AppointmentId(ActionEvent event) {}

  @FXML
  void Cancel(ActionEvent event) {
    new AppointmentManagement().Show();
  }

  @FXML
  void ExaminationDate(ActionEvent event) {}

  @FXML
  void Logout(ActionEvent event) {
    System.exit(0);
  }

  @FXML
  void appointment(ActionEvent event) {
    new AppointmentManagement().Show();
  }

  @FXML
  void cases(ActionEvent event) {
    new CaseManagement().Show();
  }

  @FXML
  void dashboard(ActionEvent event) {
    new Dashboard().Show();
  }

  @FXML
  void patients(ActionEvent event) {
    new PatientManagement().Show();
  }

  @FXML
  void users(ActionEvent event) {
    new UserManagement().Show();
  }
}
