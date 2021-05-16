package com.doan.admindonghohanquoc.Repository;


import com.doan.admindonghohanquoc.Model.Entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity,Integer> {
    @Query(value = "SELECT * FROM donghohanquoc.product where brand_id=?1 and   category_id=?2 and status=1", nativeQuery = true)
    List<ProductEntity> findBybrandidandcategoryid(Integer brandid, Integer categoryid);
    @Query(value = "SELECT * FROM donghohanquoc.product where brand_id=?1 and status=1 and price between ?2 and ?3", nativeQuery = true)
    List<ProductEntity> findBybrandid(Integer brandid, Integer priceLeft, Integer priceRight);
    @Query(value = "SELECT * FROM donghohanquoc.product where category_id=?1 and status=1", nativeQuery = true)
    List<ProductEntity> findByandcategoryid(Integer categoryid);
    @Query(value = "SELECT * FROM donghohanquoc.product where status=1", nativeQuery = true)
    Page<ProductEntity> findlistofstatus(Pageable pageable);
    @Query(value = "SELECT * FROM donghohanquoc.product where category_id=?3 and status=1 and price between ?1 and ?2", nativeQuery = true)
    List<ProductEntity> findByPriceandAndCategory(Integer priceLeft, Integer priceRight, Integer categoryId);
    @Query(value = "SELECT * FROM donghohanquoc.product where status=1 and price between ?1 and ?2", nativeQuery = true)
    List<ProductEntity> findByPrice(Integer priceLeft, Integer priceRight);
}
