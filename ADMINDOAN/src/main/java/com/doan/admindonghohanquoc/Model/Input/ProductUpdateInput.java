package com.doan.admindonghohanquoc.Model.Input;


import com.doan.admindonghohanquoc.Model.Entity.BrandEntity;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ProductUpdateInput {
    private Integer id;
    private String productname;
    private String image;
    private Integer price;
    private String description;
    private Integer status;
    private BrandEntity brandEntity;
    private Integer quantitysold;
    private Integer quantityremaining;
    private Date updatedat;
    private String updatedby;
    private List<ProductAtributeInput> productAtributeInputList;
    private List<ProductCategoriesInput> productCategoriesInputList;
}
