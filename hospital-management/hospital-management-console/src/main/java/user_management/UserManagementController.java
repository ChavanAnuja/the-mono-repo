package user_management;

import add_user.AddUser;
import appointment_management.AppointmentManagement;
import case_management.CaseManagement;
import dashboard.Dashboard;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import patient_management.PatientManagement;

public class UserManagementController {

	@FXML
    private Button addUser;

    @FXML
    private Label admin;

    @FXML
    private Button appointment;

    @FXML
    private Button cases;

    @FXML
    private Button dashboard;

    @FXML
    private Button deleteUser;

    @FXML
    private Button editUser;

    @FXML
    private Button logout;

    @FXML
    private Button patients;

    @FXML
    private Button searchUser;

    @FXML
    private Label userManagement;

    @FXML
    private Button users;

    @FXML
    void Logout(ActionEvent event) {

    }

    @FXML
    void addUser(ActionEvent event) {
         new AddUser().Show();
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
    void deleteUser(ActionEvent event) {

    }

    @FXML
    void editUser(ActionEvent event) {

    }

    @FXML
    void patients(ActionEvent event) {
            new PatientManagement().Show();
    }

    @FXML
    void searchUser(ActionEvent event) {

    }

    @FXML
    void users(ActionEvent event) {
      new UserManagement().Show();
    }

}
