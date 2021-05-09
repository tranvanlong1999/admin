package com.doan.admindonghohanquoc.Model.Entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product_atribute")
@Data
public class ProductAtributeEntity {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "Product_ID")
    private ProductEntity productentity;
    @ManyToOne
    @JoinColumn(name="Size_ID")
    private   SizeEntity sizeentity;
    @ManyToOne
    @JoinColumn(name="Color_ID")
    private ColorEntity colorentity ;
    @Column
    private Integer quantity;
    @OneToMany(mappedBy = "productAtributeEntity")
    private List<OrderItemEntity> orderItemEntityList= new ArrayList<>();
}
