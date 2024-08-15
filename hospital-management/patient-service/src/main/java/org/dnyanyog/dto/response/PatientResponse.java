package org.dnyanyog.dto.response;

import org.springframework.stereotype.Component;

@Component
public class PatientResponse {
  private int status;
  private String message;
  private PatientData data;

  public PatientResponse() {
    this.data = new PatientData(); // Initialize the data field
  }

  public static PatientResponse getInstance() {
    return new PatientResponse();
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public PatientData getData() {
    return data;
  }

  public void setData(PatientData data) {
    this.data = data;
  }
}
