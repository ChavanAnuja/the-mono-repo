package org.dnyanyog.common;

public enum ResponseCodes {
  ADD_PATIENT("Patient added successfully!!"),
  ADD_PATIENT_FAILED("MobileNumber Alredy Exist"),
  UPDATE_PATIENT("Patient updated successfully!!"),
  UPDATE_PATIENT_FAILED("Unable to update patient"),
  SEARCH_PATIENT("Patient found successfully!!"),
  SEARCH_PATIENT_FAILED("Patient not found"),
  INACTIVE_PATIENT("INACTIVE"),
  INACTIVE_PATIENT_STATUS("Patient Status is INACTIVE"),
  PATIENT_NOT_FOUND("Patient is Not Found");

  private final String message;

  ResponseCodes(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
}
