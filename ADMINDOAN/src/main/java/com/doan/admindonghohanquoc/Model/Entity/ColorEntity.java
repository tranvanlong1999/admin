package com.doan.admindonghohanquoc.Model.Entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "color")
@Data
public class ColorEntity {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "Name")
    private  String name;
    @OneToMany(mappedBy = "colorentity")
    private List<ProductAtributeEntity> productAtributeEntityList= new ArrayList<>();
}
