package org.rent.rentify.security;

import org.jspecify.annotations.NonNull;
import org.rent.rentify.model.User;
import org.rent.rentify.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public @NonNull UserDetails loadUserByUsername(@NonNull String telephone) throws UsernameNotFoundException {
        User user = userRepository.findByTelephone(telephone)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with telephone: " + telephone));

        return new org.springframework.security.core.userdetails.User(
                user.getTelephone(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()))
        );
    }
}
