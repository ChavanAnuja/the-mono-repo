package org.dnyanyog.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name = "Cases")
public class Cases {

  @Column private String patientNameEnglish;

  @Id
  @Column
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long caseId;

  @Column private Long patientId;

  @Column private Long caseNumber;

  @Column private String examinationDate;

  @Column private String symptoms;

  @Column private String prescription;
  @Column private String caseStatus;

  public String getCaseStatus() {
    return caseStatus;
  }

  public void setCaseStatus(String caseStatus) {
    this.caseStatus = caseStatus;
  }

  public Long getCaseNumber() {
    return caseNumber;
  }

  public void setCaseNumber(Long caseNumber) {
    this.caseNumber = caseNumber;
  }

  public long getCaseId() {
    return caseId;
  }

  public void setCaseId(long caseId) {
    this.caseId = caseId;
  }

  public static Cases getInstance() {
    return new Cases();
  }

  public String getPatientNameEnglish() {
    return patientNameEnglish;
  }

  public Cases setPatientNameEnglish(String patientNameEnglish) {
    this.patientNameEnglish = patientNameEnglish;
    return this;
  }

  public Long getPatientId() {
    return patientId;
  }

  public Cases setPatientId(Long patientId) {
    this.patientId = patientId;
    return this;
  }

  public String getExaminationDate() {
    return examinationDate;
  }

  public Cases setExaminationDate(String examinationDate) {
    this.examinationDate = examinationDate;
    return this;
  }

  public String getSymptoms() {
    return symptoms;
  }

  public Cases setSymptoms(String symptoms) {
    this.symptoms = symptoms;
    return this;
  }

  public String getPrescription() {
    return prescription;
  }

  public Cases setPrescription(String prescription) {
    this.prescription = prescription;
    return this;
  }
}
