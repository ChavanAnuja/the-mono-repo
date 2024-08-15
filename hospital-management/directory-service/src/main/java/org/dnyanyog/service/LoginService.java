package org.dnyanyog.service;

import org.dnyanyog.dto.request.LoginRequest;
import org.dnyanyog.dto.response.LoginResponse;

public interface LoginService {

  // ResponseEntity<LoginResponse> loginUser(LoginRequest request);
  LoginResponse login(LoginRequest request) throws Exception;

  // Optional<Users> findByMobileNumberAndPassword(String mobileNumber, String password);
}
