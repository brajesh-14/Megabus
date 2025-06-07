package com.megabus.BusBooking.controller;

import com.megabus.BusBooking.dto.GenerateOtpDto;
import com.megabus.BusBooking.dto.LoginUserDTO;
import com.megabus.BusBooking.dto.VerifyOtpDto;
import com.megabus.BusBooking.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<GenerateOtpDto> authenticateUser(@RequestBody LoginUserDTO loginRequest) {
        return loginService.loginUsers(loginRequest);
    }

    @PostMapping("/verifyOtp")
    public ResponseEntity<String> authenticateUser(@RequestBody VerifyOtpDto verifyOtpDto) {
        return loginService.verifyOtp(verifyOtpDto);
    }


}
