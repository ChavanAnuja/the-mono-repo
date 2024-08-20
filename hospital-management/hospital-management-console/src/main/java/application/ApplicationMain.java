package application;

import add_appointment.AddAppointment;
import add_case.AddCase;
import add_patient.AddPatient;
import add_user.AddUser;
import appointment_management.AppointmentManagement;
import dashboard.Dashboard;
import javafx.application.Application;
import javafx.stage.Stage;
import login.Login;
import patient_management.PatientManagement;
import stage_master.StageMaster;
import update_case.UpdateCase;
import update_patient.UpdatePatient;

public class ApplicationMain extends Application {
public static void main(String[] args) {
	launch(args);
}
	public void start(Stage primaryStage) {
		StageMaster.setStage(primaryStage);
		System.out.println("Application start");
		//new Dashboard().Show();
		new AddPatient().Show();
		//new AddAppointment().Show();
	  System.out.print(getClass().getResource(getClass().getSimpleName()+".fxml"));
		System.out.println("Application stop");
	}
}

