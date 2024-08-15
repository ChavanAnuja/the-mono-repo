package org.dnyanyog.controller;

import org.dnyanyog.dto.request.LoginRequest;
import org.dnyanyog.dto.response.LoginResponse;
import org.dnyanyog.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

  @Autowired LoginService loginService;

  @PostMapping("/api/v1/directory/validate")
  public LoginResponse login(@RequestBody LoginRequest request) throws Exception {
    LoginResponse response = loginService.login(request);
    return response;
  }
}
