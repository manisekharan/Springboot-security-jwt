package com.jwt.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {

    private String userName;
    private String password;

//    public AuthenticationRequest() {
//    }
//
//    public AuthenticationRequest(String userName, String password) {
//        this.userName = userName;
//        this.password = password;
//    }
//
//    public String getUserName() {
//        return userName;
//    }
//
//    public String getPassword() {
//        return password;
//    }

}
