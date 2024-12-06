package org.example.ecommerce.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.ecommerce.customer.CustomerEntity;
import org.example.ecommerce.customer.CustomerRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final CustomerRepository customerRepository;

    private static UserDetails convertToUserDetails(CustomerEntity entity) {
        return User
                .builder()
                .username(entity.getEmail())
                .password(entity.getPassword())
                .authorities("Customer")
                .build();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return customerRepository.findByEmail(email)
                .map(CustomUserDetailsService::convertToUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException("Email not found"));
    }
}
