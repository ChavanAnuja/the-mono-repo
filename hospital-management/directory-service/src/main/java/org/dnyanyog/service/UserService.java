package org.dnyanyog.service;

import java.util.Optional;
import org.dnyanyog.dto.request.UserRequest;
import org.dnyanyog.dto.response.UserResponse;

public interface UserService {

  Optional<UserResponse> addUser(UserRequest request) throws Exception;

  UserResponse searchUser(Long userId);

  UserResponse deleteUser(Long userId);

  UserResponse updateUser(Long userId, UserRequest request) throws Exception;
}
