package org.dnyanyog.common;

public enum ResponseCodes {
  LOGIN_SUCCESS("Active", "Login Successful"),
  LOGIN_FAIL("Inactive", "Login Failed"),
  ADD_USER_SUCCESS("Active", "User added Successful"),
  USER_FOUND_SUCCESS("Acitive", "User Found"),
  DELETE_USER("Inactive", "Inactive"),
  USER_NOT_FOUND("Not Found", "User Not Found"),
  INACTIVE_USER("Inactive", "User Status is Inactive"),
  DUPLICATE_USER("Not Found", "Mobile number and password already present"),
  SEARCH_USER_FAILED("Not Found", "User Not Found"),
  SEARCH_PATIENT("Found", "User Found"),
  UPDATE_USER_FAILED("Failed", "Update User failed"),
  UPDATE_USER("Success", "User Updated Successfully"),
  PASSWORD_MISMATCH("Not Matched", "Password and Confirm Password Does Not Match");

  private final String status;
  private final String message;

  public String getStatus() {
    return status;
  }

  public String getMessage() {
    return message;
  }

  ResponseCodes(String status, String message) {
    this.status = status;
    this.message = message;
  }
}
