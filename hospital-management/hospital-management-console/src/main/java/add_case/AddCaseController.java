package add_case;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;

import org.dnyanyog.dto.request.CaseRequest;
import org.dnyanyog.dto.response.CasesData;
import org.dnyanyog.dto.response.CasesResponse;
import org.dnyanyog.entity.Cases;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
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

public class AddCaseController {

    @FXML
    private Label admin;

    @FXML
    private Button appointment;

    @FXML
    private Button cancel;

    @FXML
    private TextField caseNumber;

    @FXML
    private Button cases;

    @FXML
    private Button dashboard;

    @FXML
    private DatePicker examinationDate;

    @FXML
    private Button logout;

    @FXML
    private TextField patientId;

    @FXML
    private Label patientManagement;

    @FXML
    private TextField patientNameEnglish;

    @FXML
    private Button patients;

    @FXML
    private TextArea prescription;

    @FXML
    private Button save;

    @FXML
    private TextArea symptomss;

    @FXML
    private Button users;
    
    @FXML
    private Label message;

    @FXML
    void Cancel(ActionEvent event) {
     new CaseManagement().Show();
    }

    @FXML
    void Logout(ActionEvent event) {
     System.exit(0);
    }

    @FXML
    void Save(ActionEvent event) {

    	patientNameEnglish.setStyle(null);
        symptomss.setStyle(null);
        prescription.setStyle(null);
        patientId.setStyle(null);
        caseNumber.setStyle(null);
        examinationDate.setStyle(null);

        // Retrieve input values from the text fields
        String patientNameEnglishText = patientNameEnglish.getText();
        String symptomsText = symptomss.getText();
        String prescriptionText = prescription.getText();
        String patientIdText = patientId.getText();
        String caseNumberText = caseNumber.getText();
        LocalDate examinationDateValue = examinationDate.getValue();

        // Validate input values
        if (patientNameEnglishText.isEmpty() || symptomsText.isEmpty() || prescriptionText.isEmpty() ||
            patientIdText.isEmpty() || caseNumberText.isEmpty() || examinationDateValue == null) {
          message.setText("All fields must be filled.");
          message.setStyle("-fx-text-fill: red;");
          return;
        }

        try {
          // Parse numeric fields
          Long patientIdLong = Long.parseLong(patientIdText);
          Long caseNumberLong = Long.parseLong(caseNumberText);

          CasesData casesData = new CasesData();
          casesData.setPatientNameEnglish(patientNameEnglishText);
          casesData.setSymptoms(symptomsText);
          casesData.setPrescription(prescriptionText);
          casesData.setPatientId(patientIdLong);
          casesData.setCaseNumber(caseNumberLong);
          casesData.setExaminationDate(examinationDateValue.toString());
          casesData.setCaseStatus("Active"); // Set default status to Active

          // URL for the POST request
          String url = "http://localhost:8083/api/v1/case/add";

          // Create the request entity with the case object
          HttpEntity<CasesData> requestEntity = new HttpEntity<>(casesData);

          // Send the POST request
          RestTemplate restTemplate = new RestTemplate();
          ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);

          // Handle the response
          if (response.getStatusCode().is2xxSuccessful()) {
            message.setText("Case saved successfully.");
            message.setStyle("-fx-text-fill: green;");
            clearFields();

            // Optionally, show a popup alert
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Case saved successfully.");
            alert.showAndWait();
          } else {
            message.setText("Error saving case.");
            message.setStyle("-fx-text-fill: red;");
          }
        } catch (NumberFormatException e) {
          message.setText("Invalid format for patient ID or case number.");
          message.setStyle("-fx-text-fill: red;");
        } catch (Exception e) {
          message.setText("An error occurred while saving the case.");
          message.setStyle("-fx-text-fill: red;");
          e.printStackTrace();
        }
    	    
 
    	
    	  }


    	void clearFields() {
    	    patientNameEnglish.clear();
    	    symptomss.clear();
    	    prescription.clear();
    	    patientId.clear();
    	    caseNumber.clear();
    	    examinationDate.setValue(null);
    	    message.setText("");
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
    void examinationDate(ActionEvent event) {

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

