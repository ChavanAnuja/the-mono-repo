package appointment_management;

import add_appointment.AddAppointment;
import case_management.CaseManagement;
import dashboard.Dashboard;
import delete_appointment.DeleteAppointment;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import patient_management.PatientManagement;
import update_appointment.UpdateAppointment;
import user_management.UserManagement;

public class AppointmentManagementController {

    @FXML
    private Button addAppointment;

    @FXML
    private Label admin;

    @FXML
    private Button appointment;

    @FXML
    private Button cases;

    @FXML
    private Button dashboard;

    @FXML
    private Button deleteAppointment;

    @FXML
    private Button editAppointment;

    @FXML
    private Button logout;

    @FXML
    private Label patientManagement;

    @FXML
    private Button patients;

    @FXML
    private Button searchAppointment;

    @FXML
    private Button users;

    @FXML
    void Logout(ActionEvent event) {
      System.exit(0);
    }

    @FXML
    void addAppointment(ActionEvent event) {
        new AddAppointment().Show();
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
    void deleteAppointment(ActionEvent event) {
       new DeleteAppointment().Show();
    }

    @FXML
    void editAppointment(ActionEvent event) {
       new UpdateAppointment().Show();
    }

    @FXML
    void patients(ActionEvent event) {
       new PatientManagement().Show();
    }

    @FXML
    void searchAppointment(ActionEvent event) {

    }

    @FXML
    void users(ActionEvent event) {
     new UserManagement().Show();
    }

}