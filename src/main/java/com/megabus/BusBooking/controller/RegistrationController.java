package com.megabus.BusBooking.controller;

import com.megabus.BusBooking.dto.RegisterUserDTO;
import com.megabus.BusBooking.entity.Users;
import com.megabus.BusBooking.service.RegistrationService;
import com.megabus.BusBooking.service.TwilioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private TwilioService twilioService;

    @PostMapping("/register")
    public ResponseEntity<String> responseEntity(@RequestBody RegisterUserDTO dto) {
        String result = registrationService.registerUsers(dto);
        if (result.equals("User already exists!")) {
            return ResponseEntity.badRequest().body(result);
        }
        String welcomeMsg = "Hello" + dto.getName() + "welcome to MEGABUS!";
        try{
            twilioService.sendSms(dto.getPhoneNo(), welcomeMsg);
        } catch (Exception e) {
            return ResponseEntity.ok("User registered, but failed to send SMS: " + e.getMessage());
        }
        return ResponseEntity.ok("User registered successfully and SMS sent.");
    }
}
