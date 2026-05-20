package co.basketlove.api.auth.service.impl;

import co.basketlove.api.auth.dto.AuthResponse;
import co.basketlove.api.auth.dto.LoginRequest;
import co.basketlove.api.auth.service.AuthService;
import co.basketlove.api.exception.BusinessException;
import co.basketlove.api.security.jwt.JwtService;
import co.basketlove.api.users.entity.User;
import co.basketlove.api.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public AuthResponse login(LoginRequest request) {

        User user = repository.findByEmail(request.email())
                .orElseThrow(() ->
                        new BusinessException("Invalid credentials"));

        boolean matches = passwordEncoder.matches(
                request.password(),
                user.getPassword()
        );

        if (!matches) {
            throw new BusinessException("Invalid credentials");
        }

        String token = jwtService.generateToken(user.getEmail());

        return new AuthResponse(token);
    }
}