package com.doan.admindonghohanquoc.Model.Entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "products_categories")
@Data
public class ProductCategoriesEntity {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name="Product_ID")
    private ProductEntity productEntity;
    @ManyToOne
    @JoinColumn(name = "Categories_ID")
    private CategoriesEntity categoriesEntity;
}
