package com.doan.admindonghohanquoc.Model.Entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Address")
@Data
public class AddressEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
    @Column
    private String name;
    
	@Column(name = "full_name")
    private String fullName;
	
    @Column
    private String phone;
    
    @Column(name = "default_status")
    private int defaultStatus;

    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
