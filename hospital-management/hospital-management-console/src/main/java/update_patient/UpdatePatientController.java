package update_patient;

import java.time.LocalDate;

import org.dnyanyog.dto.response.PatientData;
import org.dnyanyog.dto.response.PatientResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
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

public class UpdatePatientController {

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
    private DatePicker examinationDate;

    @FXML
    private TextField gender;

    @FXML
    private Button logout;

    @FXML
    private Label message;

    @FXML
    private TextField mobileNo;

    @FXML
    private Label patientManagement;

    @FXML
    private TextField patientNameEnglish;

    @FXML
    private TextField patientNameMarathi;

    @FXML
    private Button patients;

    @FXML
    private Button save;

    @FXML
    private Button search;

    @FXML
    private TextField searchPatientId;

    @FXML
    private TextField searchPatientNameEnglish;

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
    void dashboard(ActionEvent event) {
    	new Dashboard().Show();
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
    void Save(ActionEvent event) {

    	patientNameEnglish.setStyle(null);
        patientNameMarathi.setStyle(null);
        mobileNo.setStyle(null);
        gender.setStyle(null);
        BirthDate.setStyle(null);
        examinationDate.setStyle(null);
        address.setStyle(null);

        // Get input values from the text fields
        String patientNameEnglishText = patientNameEnglish.getText();
        String patientNameMarathiText = patientNameMarathi.getText();
        String mobileNoText = mobileNo.getText();
        String genderText = gender.getText();
        LocalDate birthDateValue = BirthDate.getValue();
        LocalDate examinationDateValue = examinationDate.getValue();
        String addressText = address.getText();

        // Validate input values
        if (patientNameEnglishText.isEmpty()
            || patientNameMarathiText.isEmpty()
            || mobileNoText.isEmpty()
            || genderText.isEmpty()
            || birthDateValue == null
            || examinationDateValue == null
            || addressText.isEmpty()) {
            message.setText("Please fill in all the fields.");
            message.setStyle("-fx-text-fill: red;");
            return;
        }

        try {
            // Convert dates to strings
            String birthDateStr = birthDateValue.toString();
            String examinationDateStr = examinationDateValue.toString();

            // Create JSON payload
            String jsonPayload =
                "{"
                    + "\"patientNameEnglish\": \""
                    + patientNameEnglishText
                    + "\","
                    + "\"patientNameMarathi\": \""
                    + patientNameMarathiText
                    + "\","
                    + "\"mobileNo\": \""
                    + mobileNoText
                    + "\","
                    + "\"gender\": \""
                    + genderText
                    + "\","
                    + "\"birthDate\": \""
                    + birthDateStr
                    + "\","
                    + " \"firstExaminationDate\": \""
                    + examinationDateStr
                    + "\","
                    + "\"address\": \""
                    + addressText
                    + "\""
                    + "}";

            // Define the URL with the patient ID for update
            String url = ApiEndPoints.UPDATE_PATIENT  + searchPatientId;

            // Create HTTP headers and set content type
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");
            HttpEntity<String> entity = new HttpEntity<>(jsonPayload, headers);

            // Send the POST request for update
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

            // Handle the response
            if (response.getStatusCode().is2xxSuccessful()) {
                message.setText("Data updated successfully.");
                message.setStyle("-fx-text-fill: green;");
                System.out.println("Data updated successfully!");

                // Optionally, show a popup alert for confirmation
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Data updated successfully.");
                alert.showAndWait();
            } else {
                message.setText("Failed to update data. Response Code: " + response.getStatusCode());
                message.setStyle("-fx-text-fill: red;");
                System.err.println("Failed to update data. Server returned status code: " + response.getStatusCode());
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            message.setText("Invalid Patient ID format.");
            message.setStyle("-fx-text-fill: red;");
        } catch (Exception e) {
            e.printStackTrace();
            message.setText("An error occurred while trying to update patient data.");
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
               // examinationDate.setValue(LocalDate.parse(casesResponse.getExaminationDate()));

              //  BirthDate.setValue(patient.getBirthDate());
                                
                
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
    void Users(ActionEvent event) {
          new UserManagement().Show();
    }

    @FXML
    void examinationDate(ActionEvent event) {

    }

}
