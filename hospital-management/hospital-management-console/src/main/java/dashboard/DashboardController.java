package dashboard;

import appointment_management.AppointmentManagement;
import case_management.CaseManagement;
import javafx.event.ActionEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import patient_management.PatientManagement;
import user_management.UserManagement;

public class DashboardController {

    @FXML
    private Label admin;

    @FXML
    private Button appointment;

    @FXML
    private Button cases;

    @FXML
    private Button dashboard;

    @FXML
    private Button logout;

    @FXML
    private Label patientManagement;

    @FXML
    private Button patients;

    @FXML
    private Button users;

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
