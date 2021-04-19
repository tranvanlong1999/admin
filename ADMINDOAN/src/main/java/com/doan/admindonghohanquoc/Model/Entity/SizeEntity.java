package com.doan.admindonghohanquoc.Model.Entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "size")
@Data
public class SizeEntity {
    private static final long serialVersionUID = -297553281792804396L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;
    @Column(name = "Name")
    private String name;
    @ToString.Exclude
    @OneToMany(mappedBy = "sizeentity")
    private List<ProductAtributeEntity> productAtributeEntityList = new ArrayList<>();
}
