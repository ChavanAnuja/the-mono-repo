package org.dnyanyog.service;

import java.util.Optional;
import org.dnyanyog.common.ResponseCodes;
import org.dnyanyog.dto.request.UserRequest;
import org.dnyanyog.dto.response.UserData;
import org.dnyanyog.dto.response.UserResponse;
import org.dnyanyog.entity.Users;
import org.dnyanyog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
  @Autowired private UserRepository userRepo;

  @Autowired private UserService userService;

  @Autowired private UserResponse userResponse;

  @Autowired private EncryptionService encryptionService;

  // private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

  @Autowired
  public UserServiceImpl(UserRepository userRepo, UserResponse userResponse) {
    this.userRepo = userRepo;
    this.userResponse = userResponse;
  }

  public Optional<UserResponse> addUser(UserRequest request) throws Exception {

    String encryptedPassword = encryptionService.encryp(request.getPassword());
    String encryptedConfirmPassword = encryptionService.encryp(request.getConfirmPassword());

    if (!encryptedPassword.equals(encryptedConfirmPassword)) {
      UserResponse passwordMismatchResponse = new UserResponse();
      passwordMismatchResponse.setMessage(ResponseCodes.PASSWORD_MISMATCH.getMessage());
      passwordMismatchResponse.setStatus(ResponseCodes.PASSWORD_MISMATCH.getStatus());
      return Optional.of(passwordMismatchResponse);
    }

    Optional<Users> existingUser =
        userRepo.findByMobileNumberAndPassword(request.getMobileNumber(), encryptedPassword);

    UserResponse userResponse = new UserResponse();
    if (existingUser.isPresent()) {
      userResponse.setMessage(ResponseCodes.DUPLICATE_USER.getMessage());
      userResponse.setStatus(ResponseCodes.DUPLICATE_USER.getStatus());
      return Optional.of(userResponse);
    }

    Users usersTable =
        Users.getInstance()
            .setUserNameEnglish(request.getUserNameEnglish())
            .setEmail(request.getEmail())
            .setMobileNumber(request.getMobileNumber())
            .setUserStatus(request.getUserStatus())
            .setPassword(encryptedPassword)
            .setConfirmPassword(encryptedConfirmPassword)
            .setRole(request.getRole());

    usersTable = userRepo.save(usersTable);

    userResponse.setMessage(ResponseCodes.ADD_USER_SUCCESS.getMessage());
    userResponse.setStatus(ResponseCodes.ADD_USER_SUCCESS.getStatus());

    if (userResponse.getData() == null) {
      userResponse.setData(new UserData());
    }

    userResponse.getData().setUserNameEnglish(usersTable.getUserNameEnglish());
    userResponse.getData().setRole(usersTable.getRole());
    userResponse.getData().setMobileNumber(usersTable.getMobileNumber());
    userResponse.getData().setEmail(usersTable.getEmail());
    userResponse.getData().setPassword(usersTable.getPassword());
    userResponse.getData().setUserStatus(usersTable.getUserStatus());

    return Optional.of(userResponse);
  }

  public UserResponse deleteUser(Long userId) {
    Optional<Users> existingUser = userRepo.findByUserId(userId);

    UserResponse userResponse = new UserResponse();

    if (existingUser.isPresent()) {
      Users deleteUser = (existingUser.get());
      deleteUser.setUserStatus(ResponseCodes.DELETE_USER.getMessage());
      userRepo.save(deleteUser);

      userResponse.setStatus(" ");
      userResponse.setMessage(ResponseCodes.INACTIVE_USER.getMessage());
    } else {
      userResponse.setStatus("Not Found");
      userResponse.setMessage(ResponseCodes.USER_NOT_FOUND.getMessage());
    }

    return userResponse;
  }

  @Override
  public UserResponse searchUser(Long userId) {
    Optional<Users> optionalUser = userRepo.findByUserId(userId);

    UserResponse userResponse = new UserResponse();
    if (userResponse.getData() == null) {
      userResponse.setData(new UserData());
    }

    if (optionalUser.isEmpty()) {
      userResponse.setMessage(ResponseCodes.SEARCH_USER_FAILED.getMessage());
      userResponse.setStatus(ResponseCodes.SEARCH_USER_FAILED.getStatus());
    } else {
      Users user = optionalUser.get();

      UserData userData = userResponse.getData();
      userData.setUserNameEnglish(user.getUserNameEnglish());
      userData.setEmail(user.getEmail());
      userData.setMobileNumber(user.getMobileNumber());
      userData.setRole(user.getRole());
      userResponse.setMessage(ResponseCodes.SEARCH_PATIENT.getMessage());
      userResponse.setStatus(ResponseCodes.SEARCH_PATIENT.getStatus());
    }

    return userResponse;
  }

  public UserResponse updateUser(Long userId, UserRequest request) throws Exception {
    String encryptedPassword = encryptionService.encryp(request.getPassword());
    String encryptedConfirmPassword = encryptionService.encryp(request.getConfirmPassword());

    UserResponse userResponse = new UserResponse();
    if (userResponse.getData() == null) {
      userResponse.setData(new UserData());
    }

    Optional<Users> optionalUser = userRepo.findByUserId(userId);

    if (optionalUser.isEmpty()) {
      userResponse.setStatus(ResponseCodes.UPDATE_USER_FAILED.getStatus());
      userResponse.setMessage(ResponseCodes.UPDATE_USER_FAILED.getMessage());
    } else {
      Users existingUser = optionalUser.get();
      existingUser.setUserNameEnglish(request.getUserNameEnglish());
      existingUser.setEmail(request.getEmail());
      existingUser.setMobileNumber(request.getMobileNumber());
      existingUser.setPassword(encryptedPassword);
      existingUser.setConfirmPassword(encryptedConfirmPassword);
      existingUser.setRole(request.getRole());

      userRepo.save(existingUser);
      userResponse.setMessage(ResponseCodes.UPDATE_USER.getMessage());
      userResponse.setStatus(ResponseCodes.UPDATE_USER.getStatus());

      userResponse.getData().setUserNameEnglish(existingUser.getUserNameEnglish());
      userResponse.getData().setEmail(existingUser.getEmail());
      userResponse.getData().setMobileNumber(existingUser.getMobileNumber());
      userResponse.getData().setRole(existingUser.getRole());
      userResponse.getData().setPassword(existingUser.getPassword());
    }

    return userResponse;
  }
}
