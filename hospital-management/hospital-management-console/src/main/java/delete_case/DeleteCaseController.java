package delete_case;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import appointment_management.AppointmentManagement;
import case_management.CaseManagement;
import common.ApiEndPoints;
import dashboard.Dashboard;
import javafx.event.ActionEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import patient_management.PatientManagement;
import user_management.UserManagement;

public class DeleteCaseController {

	@FXML
    private Label admin;

    @FXML
    private Button appointment;

    @FXML
    private Button cancel;

    @FXML
    private TextField caseNo;

    @FXML
    private Button cases;

    @FXML
    private Button dashboard;

    @FXML
    private Button delete;

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
    private Button search;

    @FXML
    private TextField searchCaseNumber;

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
    void Delete(ActionEvent event) {
    	String patientIdText = searchPatientId.getText();
        String caseNumberText = searchCaseNumber.getText();

        if (patientIdText.isEmpty() || caseNumberText.isEmpty()) {
            message.setText("Patient ID and Case Number must be provided.");
            message.setStyle("-fx-text-fill: red;");
            return;
        }

        try {
            Long patientIdLong = Long.parseLong(patientIdText);
            Long caseNumberLong = Long.parseLong(caseNumberText);

            String url = ApiEndPoints.DELETE_CASE + "/" + patientIdLong + "/" + caseNumberLong;

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Void> response = restTemplate.exchange(url, HttpMethod.DELETE, null, Void.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                message.setText("Case deleted successfully.");
                message.setStyle("-fx-text-fill: green;");
                clearFields();
            } else {
                message.setText("Error deleting case.");
                message.setStyle("-fx-text-fill: red;");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid Patient ID or Case Number format.");
            message.setText("Invalid Patient ID or Case Number format.");
            message.setStyle("-fx-text-fill: red;");
        }
    }
    private void clearFields() {
    	patientId.clear();
        caseNo.clear();
        patientNameEnglish.clear();
        symptomss.clear();
        prescription.clear();
        examinationDate.setValue(null);
        message.setText("");
        message.setStyle(""); // Reset message styles if necessary
    }


    @FXML
    void Logout(ActionEvent event) {
     System.exit(0);
    }

    @FXML
    void Search(ActionEvent event) {

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