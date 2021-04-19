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
    @Column(name ="Created_At")
    private Date createdat;
    @Column(name = "Created_By")
    private String createdby;
    @Column(name ="Updated_At")
    private Date updatedat;
    @Column(name = "Updated_By")
    private String updatedby;
    @Column(name="Is_For_Men")
    private Integer isformen;
    @OneToMany(mappedBy = "categoriesEntity")
    private List<ProductCategoriesEntity> categoriesEntityList= new ArrayList<>();


}
