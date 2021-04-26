package com.doan.admindonghohanquoc.Model.Entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "categories")
@Data
public class CategoriesEntity {
    public static final long serialVersionVDL=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "Name")
    private String name;
    @Column(name = "Description")
    private String description;
    @Column(name = "Status")
    private Integer status;
    @OneToMany(mappedBy = "categoriesEntity")
    private List<ProductCategoriesEntity> categoriesEntityList= new ArrayList<>();


}
