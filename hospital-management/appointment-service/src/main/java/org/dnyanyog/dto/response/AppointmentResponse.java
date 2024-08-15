package org.dnyanyog.dto.response;

import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

@Component
public class AppointmentResponse {

  @NotNull(message = "Status should not be Null")
  private int status;

  @NotNull(message = "Message should not be Null")
  private String message;

  private AppointmentData data;

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

  public AppointmentData getData() {
    return data;
  }

  public void setData(AppointmentData data) {
    this.data = data;
  }

  public static AppointmentResponse getInstance() {
    return new AppointmentResponse();
  }
}
