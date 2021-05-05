package com.doan.admindonghohanquoc.Model.OutPut;

import lombok.Data;

import java.util.Date;

@Data
public class UserOutput {
    private Integer id;
    private String password;
    private String fullname;
    private String phone;
    private String address;
    private String status;
    private String email;
}
