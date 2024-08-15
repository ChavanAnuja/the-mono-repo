package org.dnyanyog.service;

import java.util.Optional;
import org.dnyanyog.common.ResponseCodes;
import org.dnyanyog.dto.request.LoginRequest;
import org.dnyanyog.dto.response.LoginResponse;
import org.dnyanyog.entity.Users;
import org.dnyanyog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

  @Autowired EncryptionService encryptionService;

  @Autowired LoginService loginService;

  @Autowired UserRepository userRepo;

  @Override
  public LoginResponse login(LoginRequest request) throws Exception {
    String encryptedPassword = encryptionService.encryp(request.getPassword());

    Optional<Users> existingUser =
        userRepo.findByMobileNumberAndPassword(request.getMobileNumber(), request.getPassword());

    LoginResponse loginResponse = new LoginResponse();
    if (existingUser.isPresent()) {
      loginResponse.setMessage(ResponseCodes.LOGIN_SUCCESS.getMessage());
      loginResponse.setStatus(ResponseCodes.LOGIN_SUCCESS.getStatus());
    } else {
      loginResponse.setMessage(ResponseCodes.LOGIN_FAIL.getMessage());
      loginResponse.setStatus(ResponseCodes.LOGIN_FAIL.getStatus());
    }

    return loginResponse;
  }

  //	  public LoginResponse validateUser(LoginRequest loginRequest) {
  //    LoginResponse response = new LoginResponse();
  //    List<Users> usersList = userRepo.findByMobileNumber(request.getMobileNumber());
  //
  //    if (usersList.size() == 1) {
  //      Users userData = usersList.get(0);
  //
  //      String encryptedPassword = userData.getPassword();
  //      String userKey = userData.getEncryptionKey();
  //
  //      if (request.getPassword() != null && userKey != null) {
  //        String inputEncryptedPassword = encryptAES(request.getPassword(), userKey);
  //
  //        if (inputEncryptedPassword.equalsIgnoreCase(encryptedPassword)) {
  //          response.setStatus(ResponseCodes.LOGIN_SUCCESS.getStatus());
  //          response.setMessage(ResponseCodes.LOGIN_SUCCESS.getMessage());
  //
  //        } else {
  //          response.setStatus(ResponseCodes.LOGIN_FAIL.getStatus());
  //          response.setMessage(ResponseCodes.LOGIN_FAIL.getMessage());
  //        }
  //      } else {
  //        response.setStatus(ResponseCodes.LOGIN_FAIL.getStatus());
  //        response.setMessage("Password or encryption key is null");
  //      }
  //    } else {
  //      response.setStatus(ResponseCodes.LOGIN_FAIL.getStatus());
  //      response.setMessage(ResponseCodes.LOGIN_FAIL.getMessage());
  //    }
  //    return response;
  //  }
  //
  //  private String encryptAES(String input, String key) {
  //    if (input == null || key == null) {
  //      throw new IllegalArgumentException("Input and key cannot be null");
  //    }
  //    try {
  //      Cipher cipher = Cipher.getInstance("AES");
  //      SecretKeySpec secretKeySpec = new SecretKeySpec(Base64.getDecoder().decode(key), "AES");
  //      cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
  //      byte[] encryptedBytes = cipher.doFinal(input.getBytes(StandardCharsets.UTF_8));
  //      return Base64.getEncoder().encodeToString(encryptedBytes);
  //    } catch (Exception e) {
  //      throw new RuntimeException("Error encrypting with AES", e);
  //    }
  //  }
  //
  //  private String decryptAES(final String input, final String key) {
  //    if (input == null || key == null) {
  //      throw new IllegalArgumentException("Input and key cannot be null");
  //    }
  //    try {
  //      Cipher cipher = Cipher.getInstance("AES");
  //      SecretKeySpec secretKeySpec = new SecretKeySpec(Base64.getDecoder().decode(key), "AES");
  //      cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
  //      byte[] decodedBytes = Base64.getDecoder().decode(input);
  //      byte[] decryptedBytes = cipher.doFinal(decodedBytes);
  //      return new String(decryptedBytes, StandardCharsets.UTF_8);
  //    } catch (Exception e) {
  //      throw new RuntimeException("Error decrypting with AES", e);
  //    }
  //  }
}
