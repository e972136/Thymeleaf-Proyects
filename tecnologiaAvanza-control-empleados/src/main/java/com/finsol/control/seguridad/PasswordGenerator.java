package com.finsol.control.seguridad;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {
    public static void main(String arg[]){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String passwordOriginal = "123";
        String passwordEncry = encoder.encode(passwordOriginal);
        System.out.println(passwordEncry);
    }
}
