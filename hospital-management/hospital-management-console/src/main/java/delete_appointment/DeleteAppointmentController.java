package delete_appointment;

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
import dashboard.Dashboard;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import patient_management.PatientManagement;
import user_management.UserManagement;

public class DeleteAppointmentController {

    @FXML
    private Label addAppointment;

    @FXML
    private Label admin;

    @FXML
    private Button appointment;

    @FXML
    private TextField appointmentId;

    @FXML
    private TextField appointmentTime;

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
    private Button logout;

    @FXML
    private TextField patientId;

    @FXML
    private TextField patientNameEnglish;

    @FXML
    private Button patients;

    @FXML
    private Button search;

    @FXML
    private TextField searchAppointmentId;

    @FXML
    private TextField searchPatientId;
    
    @FXML
    private Label message;


    @FXML
    private Button users;

    @FXML
    void AppointmentId(ActionEvent event) {

    }

    @FXML
    void Cancel(ActionEvent event) {
    	new AppointmentManagement().Show();
    }

    @FXML
    void Delete(ActionEvent event) {
    	String appointmentIdText = appointmentId.getText();
    	String patientIdtext =patientId.getText();

        if (appointmentIdText.isEmpty()) {
            message.setText("Appointment ID must be provided.");
            message.setStyle("-fx-text-fill: red;");
            return;
        }

        String deleteUrl = ApiEndPoints.DELETE_APPOINTMENT + "/" + appointmentIdText;

        RestTemplate restTemplate = new RestTemplate();
        try {
            HttpHeaders updateHeaders = new HttpHeaders();
            updateHeaders.setContentType(MediaType.APPLICATION_JSON);
            String updatePayload = "{\"appointmentStatus\": \"Inactive\"}";
            HttpEntity<String> updateEntity = new HttpEntity<>(updatePayload, updateHeaders);


            restTemplate.delete(deleteUrl);

            message.setText("Appointment deleted successfully. Status updated to Inactive.");
            message.setStyle("-fx-text-fill: green;");

            clearFields();

        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                message.setText("Appointment not found.");
            } else {
                message.setText("Error deleting appointment data.");
            }
            message.setStyle("-fx-text-fill: red;");
        } catch (Exception e) {
            message.setText("An error occurred while deleting the appointment.");
            message.setStyle("-fx-text-fill: red;");
            e.printStackTrace();
        }
    	
    }

    @FXML
    void ExaminationDate(ActionEvent event) {

    }

    @FXML
    void Logout(ActionEvent event) {
        System.exit(0);

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

        String url = ApiEndPoints.GET_APPOINTMENT + "/" + appointmentIdText ;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Appointment> response = restTemplate.getForEntity(url, Appointment.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            Appointment appointment = response.getBody();
            if (appointment != null) {
                appointmentId.setText(String.valueOf(appointment.getAppointmentId()));
                patientNameEnglish.setText(appointment.getPatientNameEnglish());
                patientId.setText(String.valueOf(appointment.getPatientId()));
              //  examinationDate.setText(appointment.getExaminationDate());
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
