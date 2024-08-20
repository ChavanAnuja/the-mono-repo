package update_appointment;

import org.dnyanyog.entity.Appointment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import appointment_management.AppointmentManagement;
import case_management.CaseManagement;
import common.ApiEndPoints;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import patient_management.PatientManagement;
import user_management.UserManagement;

public class UpdateAppointmentController {

  @FXML private Label addAppointment;

  @FXML private Label admin;

  @FXML private Button appointment;

  @FXML private TextField appointmentId;

  @FXML private TextField appointmentTime;

  @FXML private Button cancel;

  @FXML private Button cases;

  @FXML private Button dashboard;

  @FXML private DatePicker examinationDate;

  @FXML private Button logout;

  @FXML private Label message;

  @FXML private TextField patientId;

  @FXML private TextField patientNameEnglish;

  @FXML private Button patients;

  @FXML private Button save;

  @FXML private Button search;

  @FXML private TextField searchAppointmentId;

  @FXML private TextField searchPatientId;

  @FXML private Button users;

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
  void Save(ActionEvent event) {

    String appointmentIdText = appointmentId.getText();
    String patientNameEnglishText = patientNameEnglish.getText();
    String patientIdText = patientId.getText();
    String examinationDateValue = examinationDate.getPromptText();
    String appointmentTimeText = appointmentTime.getText();

    if (appointmentIdText.isEmpty()
        || patientNameEnglishText.isEmpty()
        || patientIdText.isEmpty()
        || examinationDateValue == null
        || appointmentTimeText.isEmpty()) {
      message.setText("All fields must be filled.");
      message.setStyle("-fx-text-fill: red;");
      return;
    }

    try {
      Long appointmentIdLong = Long.parseLong(appointmentIdText);
      Long patientIdLong = Long.parseLong(patientIdText);

      Appointment updatedAppointment = new Appointment();
      updatedAppointment.setAppointmentId(appointmentIdLong);
      updatedAppointment.setPatientNameEnglish(patientNameEnglishText);
      updatedAppointment.setPatientId(patientIdLong);
     // updatedAppointment.setExaminationDate(examinationDateValue);
      updatedAppointment.setAppointmentTime(appointmentTimeText);
      updatedAppointment.setAppointmentStatus("Active");

      String url = ApiEndPoints.GET_APPOINTMENT;

      // Set HTTP headers
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);

      // Create HttpEntity with the updated appointment object and headers
      HttpEntity<Appointment> requestEntity = new HttpEntity<>(updatedAppointment, headers);

      // Send PUT request to update the appointment
      RestTemplate restTemplate = new RestTemplate();
      ResponseEntity<Appointment> response =
          restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Appointment.class);

      if (response.getStatusCode().is2xxSuccessful()) {
        message.setText("Appointment updated successfully.");
        message.setStyle("-fx-text-fill: green;");
      } else {
        message.setText("Error updating appointment.");
        message.setStyle("-fx-text-fill: red;");
      }
    } catch (NumberFormatException e) {
      message.setText("Invalid format for appointment ID or patient ID.");
      message.setStyle("-fx-text-fill: red;");
    } catch (HttpClientErrorException ex) {
      if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
        message.setText("Appointment not found.");
      } else {
        message.setText("Error updating appointment data: " + ex.getMessage());
      }
      message.setStyle("-fx-text-fill: red;");
    } catch (Exception e) {
      message.setText("An error occurred while updating the appointment.");
      message.setStyle("-fx-text-fill: red;");
      e.printStackTrace();
    }
  }

  @FXML
  void Search(ActionEvent event) {
    String appointmentIdText = searchAppointmentId.getText();
    String patientIdText = searchPatientId.getText();

    if (appointmentIdText.isEmpty() || patientIdText.isEmpty()) {
      message.setText("Appointment ID and Patient ID must be provided.");
      message.setStyle("-fx-text-fill: red;");
      return;
    }

    String url = ApiEndPoints.GET_APPOINTMENT + "/" + appointmentIdText;

    RestTemplate restTemplate = new RestTemplate();
    try {
      ResponseEntity<Appointment> response = restTemplate.getForEntity(url, Appointment.class);

      if (response.getStatusCode().is2xxSuccessful()) {
        Appointment appointment = response.getBody();
        if (appointment != null) {
          appointmentId.setText(String.valueOf(appointment.getAppointmentId()));
          patientNameEnglish.setText(appointment.getPatientNameEnglish());
          patientId.setText(String.valueOf(appointment.getPatientId()));
          appointmentTime.setText(appointment.getAppointmentTime());

          message.setText("Appointment data loaded successfully.");
          message.setStyle("-fx-text-fill: green;");
        } else {
          message.setText("Appointment not found.");
          message.setStyle("-fx-text-fill: red;");
          clearFields();
        }
      } else {
        message.setText("Error fetching appointment data.");
        message.setStyle("-fx-text-fill: red;");
        clearFields();
      }
    } catch (HttpClientErrorException ex) {
      if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
        message.setText("Appointment not found.");
      } else {
        message.setText("Error fetching appointment data: " + ex.getMessage());
      }
      message.setStyle("-fx-text-fill: red;");
      clearFields();
    } catch (Exception e) {
      message.setText("An error occurred while fetching appointment data.");
      message.setStyle("-fx-text-fill: red;");
      clearFields();
      e.printStackTrace();
    }
  }

  private void clearFields() {
    appointmentId.clear();
    patientNameEnglish.clear();
    patientId.clear();
    examinationDate.setValue(null);
    appointmentTime.clear();
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
   // new Dashboard().Show();
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
