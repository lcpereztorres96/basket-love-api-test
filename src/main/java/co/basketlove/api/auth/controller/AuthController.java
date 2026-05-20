package co.basketlove.api.auth.controller;

import co.basketlove.api.auth.dto.AuthResponse;
import co.basketlove.api.auth.dto.LoginRequest;
import co.basketlove.api.auth.service.AuthService;
import co.basketlove.api.shared.response.ApiResponse;
import co.basketlove.api.shared.response.ResponseUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping("/login")
    public ApiResponse<AuthResponse> login(
            @Valid @RequestBody LoginRequest request
    ) {

        return ResponseUtils.success(
                "Login successful",
                service.login(request)
        );
    }
}