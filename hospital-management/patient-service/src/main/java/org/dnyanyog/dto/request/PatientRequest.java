package org.dnyanyog.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import org.springframework.stereotype.Component;

@Component
public class PatientRequest {

  private Long patientId;

  @NotNull(message = "PatientName should not be Null")
  @NotBlank(message = "PatientName should not be Blank")
  private String patientNameEnglish;

  @NotNull(message = "PatientName should not be Null")
  @NotBlank(message = "PatientName should not be Blank")
  private String patientNameMarathi;

  @Min(value = 10, message = "Minium digits should be 10")
  @Max(value = 10, message = "maximum digits should be 10")
  private String mobileNumber;

  @NotNull(message = "Gender Should not be Null")
  private String gender;

  @NotNull(message = "Address should not be null")
  private String address;

  private String patientStatus;

  public String getPatientStatus() {
    return patientStatus;
  }

  public void setPatientStatus(String patientStatus) {
    this.patientStatus = patientStatus;
  }

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private LocalDate birthDate;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private LocalDate firstExaminationDate;

  public String getPatientNameEnglish() {
    return patientNameEnglish;
  }

  public void setPatientNameEnglish(String patientNameEnglish) {
    this.patientNameEnglish = patientNameEnglish;
  }

  public String getPatientNameMarathi() {
    return patientNameMarathi;
  }

  public void setPatientNameMarathi(String patientNameMarathi) {
    this.patientNameMarathi = patientNameMarathi;
  }

  public String getMobileNumber() {
    return mobileNumber;
  }

  public void setMobileNumber(String mobileNumber) {
    this.mobileNumber = mobileNumber;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public LocalDate getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
  }

  public LocalDate getFirstExaminationDate() {
    return firstExaminationDate;
  }

  public void setFirstExaminationDate(LocalDate firstExaminationDate) {
    this.firstExaminationDate = firstExaminationDate;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Long getPatientId() {
    return patientId;
  }

  public void setPatientId(Long patientId) {
    this.patientId = patientId;
  }
}
