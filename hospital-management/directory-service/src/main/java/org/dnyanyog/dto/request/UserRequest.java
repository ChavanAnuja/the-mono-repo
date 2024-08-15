package org.dnyanyog.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

@Component
public class UserRequest {
  private Long userId;

  @NotNull(message = "UserName should not be Null")
  @NotBlank(message = "UserName should not be Blank")
  private String userNameEnglish;

  @NotNull(message = "Email Id is Mendatory")
  @NotBlank(message = "Email Id should not be Blank")
  private String email;

  //  @Min(value = 10, message = "Minium digits should be 10")
  //  @Max(value = 10, message = "maximum digits should be 10")
  private String mobileNumber;

  @NotNull(message = "Role Should not be Null")
  private String role;

  @NotNull(message = "Password Should not be Null")
  private String password;

  @NotNull(message = "confirm Password Should not be Null")
  private String confirmPassword;

  private String userStatus;

  public String getUserStatus() {
    return userStatus;
  }

  public void setUserStatus(String userStatus) {
    this.userStatus = userStatus;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getUserNameEnglish() {
    return userNameEnglish;
  }

  public void setUserNameEnglish(String userNameEnglish) {
    this.userNameEnglish = userNameEnglish;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getMobileNumber() {
    return mobileNumber;
  }

  public void setMobileNumber(String mobileNumber) {
    this.mobileNumber = mobileNumber;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getConfirmPassword() {
    return confirmPassword;
  }

  public void setConfirmPassword(String confirmPassword) {
    this.confirmPassword = confirmPassword;
  }
}
