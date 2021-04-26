package com.doan.admindonghohanquoc.Repository;


import com.doan.admindonghohanquoc.Model.Entity.ProductAtributeEntity;
import com.doan.admindonghohanquoc.Model.Entity.ProductEntity;
import com.doan.admindonghohanquoc.Model.Entity.SizeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductAtributeRepository extends JpaRepository<ProductAtributeEntity, Integer> {
    @Query("SELECT e from ProductAtributeEntity e where e.productentity= :productentity")
    List<ProductAtributeEntity> findbyProductEntity(@Param("productentity") ProductEntity productEntity);
    @Query("select e from ProductAtributeEntity e where e.productentity.id=:productid")
    ProductAtributeEntity findByProductid(Integer productid);
    ProductAtributeEntity findByProductentityAndSizeentity(ProductEntity product, SizeEntity size);
}
