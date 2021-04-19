package com.doan.admindonghohanquoc.Model.Input;

import lombok.Data;

import java.util.Date;

@Data
public class UserUpdateInput {
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
    private String newPassword;
    private String reNewPassword;
    private Date forgetcodedate;
    private Date joinedat;
    private String createdby;
    private String forgetcode;
}
