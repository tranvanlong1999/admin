package com.doan.admindonghohanquoc.Repository;


import com.doan.admindonghohanquoc.Model.Entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity,Integer> {
    @Query(value = "SELECT * FROM donghohanquoc.product where brand_id=?1", nativeQuery = true)
    List<ProductEntity> findBybrandid(Integer brandid);

    @Query(value = "SELECT * FROM donghohanquoc.product where status=1", nativeQuery = true)
    Page<ProductEntity> findlistofstatus(Pageable pageable);
}
