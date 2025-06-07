package com.megabus.BusBooking.service;

import com.megabus.BusBooking.entity.Users;
import com.megabus.BusBooking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class PassengerDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException {
        // Try to find user by username or phone number
        Optional<Users> opUser = userRepository.findByUsernameOrPhoneNo(identifier, identifier);

        if (opUser.isEmpty()) {
            throw new UsernameNotFoundException("User not found with username or phone: " + identifier);
        }

        Users user = opUser.get();

        return User.withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole())))
                .build();
    }
}
