package co.basketlove.api.security.userdetails;

import co.basketlove.api.exception.ResourceNotFoundException;
import co.basketlove.api.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) {

        return repository.findByEmail(username)
                .map(CustomUserDetails::new)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));
    }
}