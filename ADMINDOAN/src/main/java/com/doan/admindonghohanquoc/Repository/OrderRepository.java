package com.doan.admindonghohanquoc.Repository;


import com.doan.admindonghohanquoc.Model.Entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository  extends JpaRepository<OrderEntity,Integer> {
}
