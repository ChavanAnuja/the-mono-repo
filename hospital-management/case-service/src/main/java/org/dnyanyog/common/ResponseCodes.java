package org.dnyanyog.common;

public enum ResponseCodes {
  ADD_CASE_SUCCESS("Active", "Case added successfully"),
  ADD_CASE_FAIL("InActive", "Case with the same caseNumber already exists"),
  DELETE_CASE_INACTIVE(" Case Status Inactive", "Inactive"),
  CASE_NOT_FOUND("Not Found", "Case Not Found"),
  CASE_FOUND("Active", "Case found"),
  UPDATE_CASE_FAIL("Failed", "Update case failed: Case not found"),
  UPDATE_CASE_SUCCESS("Active", "Case updated successfully");

  private final String message;
  private final String status;

  ResponseCodes(String message, String status) {
    this.message = message;
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public String getStatus() {
    return status;
  }
}
