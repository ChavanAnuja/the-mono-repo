package org.dnyanyog.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table
public class Patient {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long patientId;

  @Column private String patientNameEnglish;

  @Column private String patientNameMarathi;
  @Column private String mobileNumber;
  @Column private String gender;
  @Column private LocalDate birthDate;
  @Column private LocalDate firstExaminationDate;
  @Column private String address;

  @Column private String patientStatus = "ACTIVE";

  public static Patient getInstance() {
    return new Patient();
  }

  public String getPatientStatus() {
    return patientStatus;
  }

  public Patient setPatientStatus(String patientStatus) {
    this.patientStatus = patientStatus;
    return this;
  }

  public Long getPatientId() {
    return patientId;
  }

  public Patient setPatientId(Long patientId) {
    this.patientId = patientId;
    return this;
  }

  public String getPatientNameEnglish() {
    return patientNameEnglish;
  }

  public Patient setPatientNameEnglish(String patientNameEnglish) {
    this.patientNameEnglish = patientNameEnglish;
    return this;
  }

  public String getPatientNameMarathi() {
    return patientNameMarathi;
  }

  public Patient setPatientNameMarathi(String patientNameMarathi) {
    this.patientNameMarathi = patientNameMarathi;
    return this;
  }

  public String getMobileNumber() {
    return mobileNumber;
  }

  public Patient setMobileNumber(String mobileNumber) {
    this.mobileNumber = mobileNumber;
    return this;
  }

  public String getGender() {
    return gender;
  }

  public Patient setGender(String gender) {
    this.gender = gender;
    return this;
  }

  public LocalDate getBirthDate() {
    return birthDate;
  }

  public Patient setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
    return this;
  }

  public LocalDate getFirstExaminationDate() {
    return firstExaminationDate;
  }

  public Patient setFirstExaminationDate(LocalDate firstExaminationDate) {
    this.firstExaminationDate = firstExaminationDate;
    return this;
  }

  public String getAddress() {
    return address;
  }

  public Patient setAddress(String address) {
    this.address = address;
    return this;
  }
}
