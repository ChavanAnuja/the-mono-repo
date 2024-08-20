package add_patient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Base64;

import org.dnyanyog.dto.response.PatientData;
import org.dnyanyog.entity.Patient;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import appointment_management.AppointmentManagement;
import case_management.CaseManagement;
import common.ApiEndPoints;
import dashboard.Dashboard;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import patient_management.PatientManagement;
import user_management.UserManagement;

public class AddPatientController {

  @FXML private DatePicker BirthDate;

  @FXML private TextArea address;

  @FXML private Label admin;

  @FXML private Button appointment;

  @FXML private Button cancel;

  @FXML private Button cases;

  @FXML private Button dashboard;

  @FXML private DatePicker examinationDate;

  @FXML private TextField gender;

  @FXML private Button logout;

  @FXML private TextField mobileNumber;

  @FXML private Label patientManagement;

  @FXML private TextField patientNameEnglish;

  @FXML private TextField patientNameMarathi;
  
  @FXML
  private Label message;


  @FXML private Button patients;

  @FXML private Button save;

  @FXML private Button users;

  @FXML
  void Appointment(ActionEvent event) {
    new AppointmentManagement().Show();
  }

  @FXML
  void BirthDate(ActionEvent event) {}

  @FXML
  void Cancel(ActionEvent event) {
    new PatientManagement().Show();
  }

  @FXML
  void Cases(ActionEvent event) {
    new CaseManagement().Show();
  }

  @FXML
  void Dashboard(ActionEvent event) {
    new Dashboard().Show();
  }

  @FXML
  void Logout(ActionEvent event) {
    System.exit(0);
  }


  @FXML
  void Patients(ActionEvent event) {
    new PatientManagement().Show();
  }

  @FXML
  void Save(ActionEvent event) {
    
	  patientNameEnglish.setStyle(null);
	    patientNameMarathi.setStyle(null);
	    mobileNumber.setStyle(null);
	    gender.setStyle(null);
	    BirthDate.setStyle(null);
	    examinationDate.setStyle(null);
	    address.setStyle(null);

	    // Get input values from the text fields
	    String patientNameEnglishText = patientNameEnglish.getText();
	    String patientNameMarathiText = patientNameMarathi.getText();
	    String mobileNumberText = mobileNumber.getText();
	    String genderText = gender.getText();
	    LocalDate birthDateValue = BirthDate.getValue();
	    LocalDate firstExaminationDateValue = examinationDate.getValue();
	    String addressText = address.getText();

	    // Validate input values
	    if (patientNameEnglishText.isEmpty() || patientNameMarathiText.isEmpty() || mobileNumberText.isEmpty() ||
	        genderText.isEmpty() || birthDateValue == null || firstExaminationDateValue == null || addressText.isEmpty()) {
	        message.setText("All fields must be filled.");
	        message.setStyle("-fx-text-fill: red;");
	        return;
	    }

	    try {
	        // Create the patient object
	        PatientData patientData = new PatientData();
	        patientData.setPatientNameEnglish(patientNameEnglishText);
	        patientData.setPatientNameMarathi(patientNameMarathiText);
	        patientData.setMobileNumber(mobileNumberText);
	        patientData.setGender(genderText);
	        // patientData.setBirthDate(birthDateValue);
	        // patientData.setFirstExaminationDate(firstExaminationDateValue);
	        patientData.setAddress(addressText);
	        patientData.setPatientStatus("ACTIVE"); // Set default status to ACTIVE

	        // URL for the POST request
	        String url = ApiEndPoints.ADD_PATIENT;

	        // Create the request entity with the patient object
	        HttpEntity<PatientData> requestEntity = new HttpEntity<>(patientData);

	        // Send the POST request
	        RestTemplate restTemplate = new RestTemplate();
	        ResponseEntity<Patient> response = restTemplate.postForEntity(url, requestEntity, Patient.class);

	        // Handle the response
	        if (response.getStatusCode().is2xxSuccessful()) {
	            // Show success message in the label
	            message.setText("Patient saved successfully.");
	            message.setStyle("-fx-text-fill: green;");
	            
	            // Show success message in a popup
	            Alert alert = new Alert(AlertType.INFORMATION);
	            alert.setTitle("Success");
	            alert.setHeaderText(null);
	            alert.setContentText("Patient saved successfully.");
	            alert.showAndWait();

	            // Clear the fields after successful save
	            clearFields();
	        } else {
	            message.setText("Error saving patient.");
	            message.setStyle("-fx-text-fill: red;");
	        }
	    } catch (Exception e) {
	        message.setText("An error occurred while saving the patient.");
	        message.setStyle("-fx-text-fill: red;");
	        e.printStackTrace();
	    }
	}

	// Helper method to clear all input fields
	void clearFields() {
	    patientNameEnglish.clear();
	    patientNameMarathi.clear();
	    mobileNumber.clear();
	    gender.clear();
	    BirthDate.setValue(null);
	    examinationDate.setValue(null);
	    address.clear();
	    message.setText("");
	}

  @FXML
  void Users(ActionEvent event) {
    new UserManagement().Show();
  }

  @FXML
  void examinationDate(ActionEvent event) {}
}
