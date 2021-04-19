package com.doan.admindonghohanquoc.Model.Input;

import lombok.Data;

@Data
public class LoginInput {
    private String email;
    private String password;
    private boolean remember;
}
