package com.doan.admindonghohanquoc.Model.Entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
public class OrderEntity {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    // ten nguoi nhan
    @Column(name = "Recipient_Name")
    private String recipientname;
    @Column(name = "Create_Date")
    private Date createdate;
    // dia chi nguoi nhan
    @Column(name = "Recipient_Address")
    private String recipientaddress;
    @Column(name="Recipient_Phone")
    private String recipientphone;
    @Column(name = "Status")
    private Integer status;
    @Column(name = "Total")
    private Integer total;
    @Column(name = "Recipient_Email")
    private String email;
    @ManyToOne
    @JoinColumn(name="User_ID")
    private  UserEntity userEntity;
    @OneToMany(mappedBy = "orderEntity")
    private List<OrderItemEntity> orderItemEntityList= new ArrayList<>();
}
