package org.dnyanyog.dto.response;

import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

@Component
public class UpdateAppointmentResponse {
  @NotNull(message = "Status should not be Null")
  private int status;

  @NotNull(message = "Message should not be Null")
  private String message;

  private UpdateAppointmentData data;

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

  public UpdateAppointmentData getData() {
    return data;
  }

  public void setData(UpdateAppointmentData data) {
    this.data = data;
  }

  public static UpdateAppointmentResponse getInstance() {
    return new UpdateAppointmentResponse();
  }
}
