package com.megabus.BusBooking.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class OtpService {

    private Map<String, String> otpStore = new HashMap<>();

    Random random = new Random();

    public String generateOtp(String username){
        String otp = String.format("%06d", random.nextInt(999999));
        otpStore.put(username,otp);
        return otp;
    }

    public boolean validateOtp(String username, String otp){

        return otp.equals(otpStore.get(username));
    }
    public void clearOtp(String username){
        otpStore.remove(username);
    }
}
