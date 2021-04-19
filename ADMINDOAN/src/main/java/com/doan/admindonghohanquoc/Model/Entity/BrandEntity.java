package com.doan.admindonghohanquoc.Model.Entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "brands")
@Data
public class BrandEntity {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "Name")
    private String name;
    @Column(name = "Description")
    private String description;

}
