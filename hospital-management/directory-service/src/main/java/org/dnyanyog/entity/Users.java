package org.dnyanyog.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.stereotype.Component;

@Table
@Entity
@Component
public class Users {

  @Id
  @Column
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long userId;

  @Column private String userNameEnglish;

  @Column private String email;

  @Column private String mobileNumber;

  @Column private String role;

  @Column private String password;

  @Column private String confirmPassword;

  @Column private String userStatus;

  @Column private String aesKey;

  public String getAesKey() {
    return aesKey;
  }

  public Users setAesKey(String aesKey) {
    this.aesKey = aesKey;
    return this;
  }

  public String getUserStatus() {
    return userStatus;
  }

  public Users setUserStatus(String userStatus) {
    this.userStatus = userStatus;
    return this;
  }

  public static Users getInstance() {
    return new Users();
  }

  public Long getUserId() {
    return userId;
  }

  public Users setUserId(Long userId) {
    this.userId = userId;
    return this;
  }

  public String getUserNameEnglish() {
    return userNameEnglish;
  }

  public Users setUserNameEnglish(String userNameEnglish) {
    this.userNameEnglish = userNameEnglish;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public Users setEmail(String email) {
    this.email = email;
    return this;
  }

  public String getMobileNumber() {
    return mobileNumber;
  }

  public Users setMobileNumber(String mobileNumber) {
    this.mobileNumber = mobileNumber;
    return this;
  }

  public String getRole() {
    return role;
  }

  public Users setRole(String role) {
    this.role = role;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public Users setPassword(String password) {
    this.password = password;
    return this;
  }

  public String getConfirmPassword() {
    return confirmPassword;
  }

  public Users setConfirmPassword(String confirmPassword) {
    this.confirmPassword = confirmPassword;
    return this;
  }

  public String getEncryptionKey() { // TODO Auto-generated method stub
    return null;
  }
}
