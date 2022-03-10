package com.bridgelabz.bookstore.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

public @Data
class UserDTO {

    public String first_name;

    public String last_name;

    public String kyc;


    public LocalDateTime birth_date;

    public LocalDateTime registration_date = LocalDateTime.now();

    public LocalDateTime update_date;

    public String password;

    public String email_id;

    public Boolean verify;

    public int otp;

    public LocalDate purchase_date;

    public LocalDate expiry_date;
}
