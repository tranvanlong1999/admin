package com.doan.admindonghohanquoc.Model.Entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "order_item")
@Data
public class OrderItemEntity {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name="Product_Atribute_ID")
    private ProductAtributeEntity productAtributeEntity;
    @ManyToOne
    @JoinColumn(name = "Order_ID")
    private OrderEntity orderEntity;
    @Column(name = "Quantity")
    private Integer quantity;
    @Column(name="Price")
    private Integer price;
    @Column(name = "Sale")
    private Double sale;
}
