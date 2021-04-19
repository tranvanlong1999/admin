package com.doan.admindonghohanquoc.Model.OutPut;

import lombok.Data;

import java.util.Date;

@Data
public class UserOutput {
    private Integer id;
    private String username;
    private String password;
    private String fullname;
    private String phone;
    private String address;
    private int status;
    private int role;
    private String email;
    private int sex;
    private Date updatedat;
    private String updatedby;
    private Date forgetcodedate;
    private Date joinedat;
    private String createdby;
    private String forgetcode;
}
