package co.basketlove.api.users.service.impl;

import co.basketlove.api.exception.BusinessException;
import co.basketlove.api.users.dto.CreateUserRequest;
import co.basketlove.api.users.dto.UserResponse;
import co.basketlove.api.users.entity.User;
import co.basketlove.api.users.mapper.UserMapper;
import co.basketlove.api.users.repository.UserRepository;
import co.basketlove.api.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponse create(CreateUserRequest request) {

        if (repository.existsByEmail(request.email())) {
            throw new BusinessException("Email already registered");
        }

        User user = new User();

        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));

        repository.save(user);

        return mapper.toResponse(user);
    }
}