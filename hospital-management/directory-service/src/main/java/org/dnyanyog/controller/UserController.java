package org.dnyanyog.controller;

import jakarta.validation.Valid;
import java.util.Optional;
import org.dnyanyog.dto.request.UserRequest;
import org.dnyanyog.dto.response.UserResponse;
import org.dnyanyog.service.UserService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  private UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/api/v1/directory/add")
  public Optional<UserResponse> addUser(@Valid @RequestBody UserRequest request) throws Exception {
    return userService.addUser(request);
  }

  @DeleteMapping(path = "/api/v1/directory/{userId}")
  public UserResponse deleteUser(@PathVariable Long userId) {
    UserResponse patientResponse = userService.deleteUser(userId);
    userService.deleteUser(userId);
    return userService.deleteUser(userId);
  }

  @GetMapping(path = "/api/v1/directory/{userId}")
  public UserResponse searchUser(@PathVariable Long userId) {
    return userService.searchUser(userId);
  }

  //  @PostMapping("/api/v1/directory/update/{userId}")
  //  public UserResponse updateUser(@PathVariable Long userId, @RequestBody UserRequest request)
  //      throws Exception {
  //    return userService.updateUser(userId, request);
  //  }
}
