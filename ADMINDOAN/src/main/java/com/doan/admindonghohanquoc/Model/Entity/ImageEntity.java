package com.doan.admindonghohanquoc.Model.Entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Images")
@Data
public class ImageEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@Column
	private String path;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private ProductEntity product;
}
