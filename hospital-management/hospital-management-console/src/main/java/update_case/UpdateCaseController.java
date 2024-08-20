package update_case;


import java.time.LocalDate;

import org.dnyanyog.dto.response.CasesData;
import org.dnyanyog.dto.response.CasesResponse;
import org.springframework.http.ResponseEntity;
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


public class UpdateCaseController {
	
	@FXML
    private Label admin;

    @FXML
    private Button appointment;

    @FXML
    private Button cancel;

    @FXML
    private TextField caseNo;

    @FXML
    private TextField searchCaseNumber;

    @FXML
    private Button cases;

    @FXML
    private Button dashboard;

    @FXML
    private DatePicker examinationDate;

    @FXML
    private Button logout;

    @FXML
    private Label message;

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
    private Button search;

    @FXML
    private TextField searchPatientId;

    @FXML
    private TextArea symptomss;

    @FXML
    private Button users;

    @FXML
    void Cancel(ActionEvent event) {
     new CaseManagement().Show();
    }

    @FXML
    void Logout(ActionEvent event) {

    }

    @FXML
    void Save(ActionEvent event) {

    }

    @FXML
    void Search(ActionEvent event) {

    	String patientIdText = searchPatientId.getText();
       String caseNumberText = searchCaseNumber.getText();

        if (patientIdText.isEmpty() || caseNumberText.isEmpty()) {
          message.setText("Patient ID and Case Number must be provided.");
          message.setStyle("-fx-text-fill: red;");
          return;
        }

    
          String url = ApiEndPoints.UPDATE_CASE + "/" + patientIdText; // Append patientId to the URL

          
          RestTemplate restTemplate = new RestTemplate();
          ResponseEntity<CasesResponse> response = restTemplate.getForEntity(url, CasesResponse.class);
          

          if (response.getStatusCode().is2xxSuccessful()) {
        	  CasesResponse casesResponse = response.getBody();
            if (casesResponse != null) {
            	CasesData caseData = casesResponse.getData();
              System.out.println("Patient ID: " + casesResponse.getData());
              System.out.println("Patient Name English: " + casesResponse.getData());
              System.out.println("Case Number: " + casesResponse.getData());
              System.out.println("Examination Date: " + casesResponse.getData());
              System.out.println("Symptoms: " + casesResponse.getData());
              System.out.println("Prescription: " + casesResponse.getData());

              patientNameEnglish.setText(caseData.getPatientNameEnglish());
              symptomss.setText(caseData.getSymptoms());
              prescription.setText(caseData.getPrescription());
              examinationDate.setValue(LocalDate.parse(caseData.getExaminationDate()));
              patientId.setText(String.valueOf(caseData.getPatientId()));
              caseNo.setText(String.valueOf(caseData.getCaseNumber()));

              message.setText("Data loaded successfully.");
              message.setStyle("-fx-text-fill: green;");
            } else {
              System.out.println("Patient not found.");
              message.setText("Patient not found.");
              message.setStyle("-fx-text-fill: red;");
              clearFields();
            }
          } else {
            System.out.println("Error fetching patient data.");
            message.setText("Error fetching patient data.");
            message.setStyle("-fx-text-fill: red;");
            clearFields();
          }
        
      }

      private void clearFields() {
        patientNameEnglish.setText("Not Found");
        caseNo.setText("");
        patientId.setText("");
        symptomss.setText("");
        examinationDate.setValue(null);
        prescription.setText("");
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
