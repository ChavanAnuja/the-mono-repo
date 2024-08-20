package patient_management;

import add_patient.AddPatient;
import appointment_management.AppointmentManagement;
import case_management.CaseManagement;
import dashboard.Dashboard;
import delete_patient.DeletePatient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import update_patient.UpdatePatient;
import user_management.UserManagement;

public class PatientManagementController {

    @FXML
    private Button addPatient;

    @FXML
    private Label admin;

    @FXML
    private Button appointment;

    @FXML
    private Button cases;

    @FXML
    private Button dashboard;

    @FXML
    private Button deletePatient;

    @FXML
    private Button editPatient;

    @FXML
    private Button logout;

    @FXML
    private Label patientManagement;

    @FXML
    private Button patients;

    @FXML
    private Button searchPatient;

    @FXML
    private Button users;

    @FXML
    void addPatient(ActionEvent event) {
      new AddPatient().Show();
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
    void deletePatient(ActionEvent event) {
       new DeletePatient().Show();
    }

    @FXML
    void EditPatient(ActionEvent event) {
     new UpdatePatient().Show();
    }

    @FXML
    void logout(ActionEvent event) {
      System.exit(0);
    }

    @FXML
    void patients(ActionEvent event) {
      new PatientManagement().Show();
    }

    @FXML
    void searchPatient(ActionEvent event) {

    }

    @FXML
    void users(ActionEvent event) {
      new UserManagement().Show();
    }

}
