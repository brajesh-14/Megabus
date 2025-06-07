package com.megabus.BusBooking.dto;

import lombok.Data;

@Data
public class RegisterUserDTO {

    private String name;
    private String email;
    private String username;
    private String phoneNo;
    private String password;
    private String role;  // "USER" or "ADMIN"
}
