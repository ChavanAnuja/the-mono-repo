package case_management;

import add_case.AddCase;
import appointment_management.AppointmentManagement;
import dashboard.Dashboard;
import delete_case.DeleteCase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import patient_management.PatientManagement;
import update_case.UpdateCase;
import user_management.UserManagement;

public class CaseManagementController {

	
    @FXML
    private Button addCase;

    @FXML
    private Label admin;

    @FXML
    private Button appointment;

    @FXML
    private Button cases;

    @FXML
    private Button dashboard;

    @FXML
    private Button deleteCase;

    @FXML
    private Button editCase;

    @FXML
    private Button logout;

    @FXML
    private Label patientManagement;

    @FXML
    private Button patients;

    @FXML
    private Button searchCase;

    @FXML
    private Button users;

    @FXML
    void Logout(ActionEvent event) {
        System.exit(0);

    }

    @FXML
    void addCase(ActionEvent event) {
        new AddCase().Show();

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
    void deleteCase(ActionEvent event) {
        new DeleteCase().Show();

    }

    @FXML
    void editCase(ActionEvent event) {
        new UpdateCase().Show();

    }

    @FXML
    void patients(ActionEvent event) {
        new PatientManagement().Show();

    }

    @FXML
    void searchCase(ActionEvent event) {

    }

    @FXML
    void users(ActionEvent event) {
        new UserManagement().Show();

    }

}
