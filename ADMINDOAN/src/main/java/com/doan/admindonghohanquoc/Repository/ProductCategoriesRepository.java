package com.doan.admindonghohanquoc.Repository;


import com.doan.admindonghohanquoc.Model.Entity.ProductCategoriesEntity;
import com.doan.admindonghohanquoc.Model.Entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCategoriesRepository extends JpaRepository<ProductCategoriesEntity,Integer> {
    List<ProductCategoriesEntity> findByProductEntity(ProductEntity productEntity);
}
