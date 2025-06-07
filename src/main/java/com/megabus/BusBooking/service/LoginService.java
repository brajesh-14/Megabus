package com.megabus.BusBooking.service;

import com.megabus.BusBooking.dto.GenerateOtpDto;
import com.megabus.BusBooking.dto.LoginUserDTO;
import com.megabus.BusBooking.dto.VerifyOtpDto;
import org.springframework.http.ResponseEntity;

public interface LoginService {

    public ResponseEntity<GenerateOtpDto> loginUsers(LoginUserDTO loginUserDTO);

    public ResponseEntity<String> verifyOtp(VerifyOtpDto verifyOtpDto);
}
