package co.basketlove.api.users.service;

import co.basketlove.api.users.dto.CreateUserRequest;
import co.basketlove.api.users.dto.UserResponse;

public interface UserService {

    UserResponse create(CreateUserRequest request);
}