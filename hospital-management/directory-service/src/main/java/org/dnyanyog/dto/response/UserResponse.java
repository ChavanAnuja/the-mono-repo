package org.dnyanyog.dto.response;

import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

@Component
public class UserResponse {

  @NotNull(message = "Status Should not be Null")
  private String status;

  @NotNull(message = "Message Should not be Null")
  private String message;

  private UserData data;

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public UserData getData() {
    return data;
  }

  public void setData(UserData data) {
    this.data = data;
  }

  public static UserResponse getInstance() { // TODO Auto-generated method stub
    return null;
  }
}
