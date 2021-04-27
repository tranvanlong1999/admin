package com.doan.admindonghohanquoc.Model.OutPut;

import com.doan.admindonghohanquoc.Model.Entity.BrandEntity;
import lombok.Data;

import java.util.Date;

@Data
public class ProductOutput {
    private Integer id;
    private String productname;
    private String image;
    private Integer price;
    private String description;
    private String status;
    private BrandEntity brandentity;
    private Integer quantitysold;
    private Integer quantityremaining;
    private Date createdat;
    private String createdby;
    private Date updatedat;
    private String updatedby;
    private String path;
    private String PriceStr;
}
