package com.doan.admindonghohanquoc.Repository;

import com.doan.admindonghohanquoc.Model.Entity.ImageEntity;
import com.doan.admindonghohanquoc.Model.Entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
@Repository
@Transactional
public interface ImageRepository extends JpaRepository<ImageEntity,Integer> {
    List<ImageEntity> findByProduct(ProductEntity product);

    /*@Query("SELECT new com.tuanpv.model.output.ImageOutput(i.path) FROM Images i where i.product.id = :productId")
    List<ImageOutput> findByProduct(@Param("productId") int productId);*/
}
