package org.dnynayog.common;

public enum ResponseCodes {
  ADD_APPOINTMENT_SUCCESS("Active"),

  DUPLICATE_APPOINTMENT("Appointment with this date and time already exists."),

  DELETE_APPOINTMENT("Inactive"),

  INACTIVE_APPOINTMENT("Appointment Status is INACTIVE"),

  APPOINTMENT_NOT_FOUND("Appointments is Not Found"),

  APPOINTMENT_FOUND("Appointment Found"),

  APPOINTMENT_UPDATE("Appointment Updated Successfully");

  private final String message;

  ResponseCodes(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
}
