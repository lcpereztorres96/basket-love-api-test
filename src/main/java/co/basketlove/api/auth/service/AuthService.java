package co.basketlove.api.auth.service;

import co.basketlove.api.auth.dto.AuthResponse;
import co.basketlove.api.auth.dto.LoginRequest;

public interface AuthService {

    AuthResponse login(LoginRequest request);
}