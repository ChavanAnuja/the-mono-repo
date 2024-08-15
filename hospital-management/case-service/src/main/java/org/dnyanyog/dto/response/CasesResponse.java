package org.dnyanyog.dto.response;

import org.springframework.stereotype.Component;

@Component
public class CasesResponse {
  private String message;
  private String status;
  private CasesData data;

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public CasesData getData() {
    return data;
  }

  public void setData(CasesData data) {
    this.data = data;
  }

  public static CasesResponse getInstance() { // TODO Auto-generated method stub
    return new CasesResponse();
  }
}
