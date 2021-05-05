package com.doan.admindonghohanquoc.Model.Entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user") // map voi database
public class UserEntity {
    private static final long serialVersionUID = -297553281792804396L;
    @Id // primary key va not null
    @GeneratedValue(strategy = GenerationType.IDENTITY)// tự tăng ID
    private int ID;
    @Column(name = "Password")
    private String passWord;
    @Column(name = "Full_Name")
    private String fullName;
    @Column(name = "Phone")
    private String phone;
    @Column(name = "Address")
    private String address;
    @Column(name = "Forget_Code")
    private String forgetCode;
    @Column(name = "Status")
    private int status;
    @Column(name = "Role")
    private int role;
    @Column(name = "Email")
    private String email;
    @Column(name = "Forget_Code_Date")
    private Date forgetCodeDate;
    @Column(name = "joined_At")
    private Date joinedAt;
    @Column(name = "Created_By")
    private String createBy;
    @Column(name = "Updated_By")
    private String udatedBy;
    @Column(name = "Updated_At")
    private Date updatedAt;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getForgetCode() {
        return forgetCode;
    }

    public void setForgetCode(String forgetCode) {
        this.forgetCode = forgetCode;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getForgetCodeDate() {
        return forgetCodeDate;
    }

    public void setForgetCodeDate(Date forgetCodeDate) {
        this.forgetCodeDate = forgetCodeDate;
    }

    public Date getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(Date joinedAt) {
        this.joinedAt = joinedAt;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUdatedBy() {
        return udatedBy;
    }

    public void setUdatedBy(String udatedBy) {
        this.udatedBy = udatedBy;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
