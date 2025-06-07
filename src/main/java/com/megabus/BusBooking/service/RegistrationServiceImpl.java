package com.megabus.BusBooking.service;

import com.megabus.BusBooking.dto.RegisterUserDTO;
import com.megabus.BusBooking.entity.Booking;
import com.megabus.BusBooking.entity.Users;
import com.megabus.BusBooking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegistrationServiceImpl implements RegistrationService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public String registerUsers(RegisterUserDTO dto) {

        Optional<Users> existingUser = userRepository.findByUsernameOrPhoneNo(dto.getUsername(), dto.getPhoneNo());
        if (existingUser.isPresent()) {
            return "User already exists!";
        }
        Users user = new Users();

        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setUsername(dto.getUsername());
        user.setPhoneNo(dto.getPhoneNo());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(Users.Role.USER);

        userRepository.save(user);
        return "User registered successfully!";
    }
}
