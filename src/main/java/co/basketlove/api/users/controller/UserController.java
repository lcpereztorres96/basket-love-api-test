package co.basketlove.api.users.controller;

import co.basketlove.api.shared.response.ApiResponse;
import co.basketlove.api.shared.response.ResponseUtils;
import co.basketlove.api.users.dto.CreateUserRequest;
import co.basketlove.api.users.dto.UserResponse;
import co.basketlove.api.users.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping
    public ApiResponse<UserResponse> create(
            @Valid @RequestBody CreateUserRequest request
    ) {

        return ResponseUtils.success(
                "User created successfully",
                service.create(request)
        );
    }
}