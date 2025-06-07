package com.megabus.BusBooking.service;

import com.megabus.BusBooking.component.JWTUtils;
import com.megabus.BusBooking.dto.GenerateOtpDto;
import com.megabus.BusBooking.dto.LoginUserDTO;
import com.megabus.BusBooking.dto.VerifyOtpDto;
import com.megabus.BusBooking.entity.Users;
import com.megabus.BusBooking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PassengerDetailService passengerDetailService;

    @Autowired
    private TwilioService twilioService;

    @Autowired
    private OtpService otpService;

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<GenerateOtpDto> loginUsers(LoginUserDTO loginUserDTO) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginUserDTO.getUsername(),
                            loginUserDTO.getPassword()
                    )
            );

            Optional<Users> optionalUser = userRepository.findByUsernameOrPhoneNo(
                    loginUserDTO.getUsername(), loginUserDTO.getPhoneNo());

            if (optionalUser.isEmpty()) {
                return ResponseEntity.badRequest().body(new GenerateOtpDto(null, "User not found"));
            }

            Users user = optionalUser.get();
            String otp = otpService.generateOtp(user.getUsername());
            String message = "Your MegaBus login OTP is: " + otp;
           // twilioService.sendSms(user.getPhoneNo(), message);

            return ResponseEntity.ok(new GenerateOtpDto(otp, "OTP sent to registered phone number"));

        } catch (BadCredentialsException e) {
            return ResponseEntity.badRequest().body(new GenerateOtpDto(null, "Invalid credentials"));
        }
    }

    @Override
    public ResponseEntity<String> verifyOtp(VerifyOtpDto verifyOtpDto) {
        String username = verifyOtpDto.getUsername();
        String otp = verifyOtpDto.getOtp();

        if (otpService.validateOtp(username, otp)) {
            UserDetails userDetails = passengerDetailService.loadUserByUsername(username);
            String role = userDetails.getAuthorities().iterator().next().getAuthority().replace("ROLE_", "");

            String token = jwtUtils.generateToken(username, role);
            otpService.clearOtp(username);

            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.badRequest().body("Invalid or expired OTP");
        }
    }
}
