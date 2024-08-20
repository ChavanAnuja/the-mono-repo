package add_user;

import org.dnyanyog.dto.response.UserData;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import org.dnyanyog.entity.Users;
import appointment_management.AppointmentManagement;
import case_management.CaseManagement;
import common.ApiEndPoints;
import dashboard.Dashboard;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import patient_management.PatientManagement;
import user_management.UserManagement;

public class AddUserController {

    @FXML
    private Label addUser;

    @FXML
    private Label admin;

    @FXML
    private Button appointment;

    @FXML
    private Button camcel;

    @FXML
    private Button cases;

    @FXML
    private PasswordField confirmPassword;


    @FXML
    private Button dashboard;

    @FXML
    private TextField email;

    @FXML
    private Button logout;

    @FXML
    private Label message;

    @FXML
    private TextField mobileNo;

    @FXML
    private PasswordField password;

    @FXML
    private Button patients;

    @FXML
    private TextField role;

    @FXML
    private Button save;

    @FXML
    private TextField usernameEnglish;

    @FXML
    private Button users;

    @FXML
    void Appointment(ActionEvent event) {
         new AppointmentManagement().Show();
    }

    @FXML
    void Cancel(ActionEvent event) {
      new UserManagement().Show();
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

    	usernameEnglish.setStyle(null);
        email.setStyle(null);
        mobileNo.setStyle(null);
        password.setStyle(null);
        confirmPassword.setStyle(null);
        role.setStyle(null);

        // Retrieve input values from UI fields
        String userNameEnglishText = usernameEnglish.getText();
        String emailText = email.getText();
        String mobileNumberText = mobileNo.getText();
        String passwordText = password.getText();
        String confirmPasswordText = confirmPassword.getText();
        String roleText = role.getText();

        // Validate input fields
        if (userNameEnglishText.isEmpty() || emailText.isEmpty() || mobileNumberText.isEmpty() ||
            passwordText.isEmpty() || confirmPasswordText.isEmpty() || roleText.isEmpty()) {
            message.setText("All fields must be filled.");
            message.setStyle("-fx-text-fill: red;");
            return;
        }

        // Validate password match
        if (!passwordText.equals(confirmPasswordText)) {
            message.setText("Passwords do not match.");
            message.setStyle("-fx-text-fill: red;");
            return;
        }

        try {
            // Create UserData object with input values
            UserData userData = new UserData();
            userData.setUserNameEnglish(userNameEnglishText);
            userData.setEmail(emailText);
            userData.setMobileNumber(mobileNumberText);
            userData.setPassword(passwordText);
            userData.setConfirmPassword(confirmPasswordText);
            userData.setRole(roleText);

            // Define the URL for adding a new user
            String url = ApiEndPoints.ADD_USER;

            // Create HTTP request entity with UserData as the body
            HttpEntity<UserData> requestEntity = new HttpEntity<>(userData);

            // Send POST request to add the user
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Users> response = restTemplate.postForEntity(url, requestEntity, Users.class);

            // Handle response
            if (response.getStatusCode().is2xxSuccessful()) {
                message.setText("User added successfully.");
                message.setStyle("-fx-text-fill: green;");
                clearFields(); // Optionally clear fields after successful addition

                // Show pop-up message
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("User Added");
                alert.setHeaderText("Success!!");
                alert.setContentText("User added successfully!");
                alert.showAndWait();
            } else {
                message.setText("Error adding user.");
                message.setStyle("-fx-text-fill: red;");
            }
        } catch (Exception e) {
            message.setText("An error occurred while saving the user.");
            message.setStyle("-fx-text-fill: red;");
            e.printStackTrace();
        }
        
    }

    void clearFields() {
        usernameEnglish.clear();
        email.clear();
        mobileNo.clear();
        password.clear();
        confirmPassword.clear();
        role.clear();
        message.setText("");
    }


    @FXML
    void Users(ActionEvent event) {
          new UserManagement().Show();
    }

}