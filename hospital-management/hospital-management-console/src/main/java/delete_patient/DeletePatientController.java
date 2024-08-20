package delete_patient;

import java.time.LocalDate;

import org.dnyanyog.dto.response.PatientData;
import org.dnyanyog.dto.response.PatientResponse;
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
import dashboard.Dashboard;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import patient_management.PatientManagement;
import user_management.UserManagement;

public class DeletePatientController {
	
	 @FXML
	    private DatePicker BirthDate;

	    @FXML
	    private TextArea address;

	    @FXML
	    private Label admin;

	    @FXML
	    private Button appointment;

	    @FXML
	    private Button cancel;

	    @FXML
	    private Button cases;

	    @FXML
	    private Button dashboard;

	    @FXML
	    private Button delete;

	    @FXML
	    private DatePicker examinationDate;

	    @FXML
	    private TextField gender;

	    @FXML
	    private Button logout;

	    @FXML
	    private TextField mobileNo;

	    @FXML
	    private TextField searchPatientId;

	    @FXML
	    private TextField searchPatientNameEnglish;
	    @FXML
	    private Label patientManagement;

	    @FXML
	    private TextField patientNameEnglish;

	    @FXML
	    private TextField patientNameMarathi;
	    
	    @FXML
	    private Label message;

	    @FXML
	    private Button patients;

	    @FXML
	    private Button search;

	    @FXML
	    private Button users;

	    @FXML
	    void Appointment(ActionEvent event) {
          new AppointmentManagement().Show();
	    }

	    @FXML
	    void BirthDate(ActionEvent event) {

	    }

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
	    void Delete(ActionEvent event) {

	    	String patientIdText = searchPatientId.getText();

	    	if (patientIdText.isEmpty()) {
	    	    message.setText("Patient ID must be provided.");
	    	    message.setStyle("-fx-text-fill: red;");
	    	    return;
	    	}

	    	String deleteUrl = ApiEndPoints.DELETE_PATIENT + "/" + patientIdText;

	    	RestTemplate restTemplate = new RestTemplate();
	    	try {
	    	    // Step 1: Update patient status to "Inactive"
	    	    HttpHeaders updateHeaders = new HttpHeaders();
	    	    updateHeaders.setContentType(MediaType.APPLICATION_JSON);
	    	    String updatePayload = "{\"patientStatus\": \"Inactive\"}";
	    	    HttpEntity<String> updateEntity = new HttpEntity<>(updatePayload, updateHeaders);

	    	    // Send the update request (optional, depending on your backend design)
	    	    // restTemplate.exchange(updateUrl, HttpMethod.PUT, updateEntity, String.class);

	    	    // Step 2: Delete the patient
	    	    restTemplate.delete(deleteUrl);

	    	    // Clear UI elements or show appropriate message
	    	    clearFields(); // Example method to clear input fields
	    	    message.setText("Patient deleted successfully. Status updated to Inactive.");
	    	    message.setStyle("-fx-text-fill: green;");

	    	    // Optionally clear or update UI elements displaying patient details
	    	    patientNameEnglish.setText(""); // Clear patient name display or set to empty
	    	    // Other UI updates as needed
	    	} catch (HttpClientErrorException ex) {
	    	    if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
	    	        message.setText("Patient not found.");
	    	    } else {
	    	        message.setText("Error deleting patient data.");
	    	    }
	    	    message.setStyle("-fx-text-fill: red;");
	    	}

	    	
	    }


	    @FXML
	    void Search(ActionEvent event) {
	    	String patientIdText = searchPatientId.getText();
	        String patientnameEnglish = searchPatientNameEnglish.getText();

	        if (patientIdText.isEmpty() || patientnameEnglish.isEmpty()) {
	            message.setText("Patient ID and Patient Name English must be provided.");
	            message.setStyle("-fx-text-fill: red;");
	            return;
	        }

	        String url = ApiEndPoints.UPDATE_PATIENT + "/" + patientIdText; 

	        RestTemplate restTemplate = new RestTemplate();
	        ResponseEntity<PatientResponse> response = restTemplate.getForEntity(url, PatientResponse.class);

	        if (response.getStatusCode().is2xxSuccessful()) {
	            PatientResponse patientResponse = response.getBody();
	            if (patientResponse != null && patientResponse.getData() != null) {
	                PatientData patient = patientResponse.getData();
	                patientNameEnglish.setText(patient.getPatientNameEnglish());
	                patientNameMarathi.setText(patient.getPatientNameMarathi());
	                mobileNo.setText(patient.getMobileNumber());
	                gender.setText(patient.getGender());
	                address.setText(patient.getAddress());
	                
	               // BirthDate.setValue(patient.getBirthDate());
	                                
	                
	                message.setText("Data loaded successfully.");
	                message.setStyle("-fx-text-fill: green;");
	            } else {
	                message.setText("Patient not found.");
	                message.setStyle("-fx-text-fill: red;");
	                clearFields();
	            }
	        } else {
	            message.setText("Error fetching patient data.");
	            message.setStyle("-fx-text-fill: red;");
	            clearFields();
	        }
	    }
	    
	    private void clearFields() {
	        patientNameEnglish.setText("Not Found");
	        patientNameMarathi.setText("");
	        mobileNo.setText("");
	        gender.setText("");
	        BirthDate.setValue(null);
	        examinationDate.setValue(null);
	        address.setText("");
	      }

	    @FXML
	    void Logout(ActionEvent event) {

	    }

	    @FXML
	    void MobileNumber(ActionEvent event) {

	    }

	    @FXML
	    void Patients(ActionEvent event) {
	          new PatientManagement().Show();

	    }

	   
	    @FXML
	    void Users(ActionEvent event) {
            new UserManagement().Show();
	    }

	    @FXML
	    void examinationDate(ActionEvent event) {

	    }
}
