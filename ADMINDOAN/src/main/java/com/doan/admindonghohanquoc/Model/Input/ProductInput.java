package com.doan.admindonghohanquoc.Model.Input;

import com.doan.admindonghohanquoc.Model.Entity.BrandEntity;
import lombok.Data;

import java.util.List;

@Data
public class ProductInput {
    private String productname;
    private String image;
    private Integer price;
    private String description;
    private BrandEntity brandentity;
    private Integer quantitysold;
    private Integer quantityremaining;
    private String createdby;
    private List<ProductAtributeInput> productAtributeInputList;
    private List<ProductCategoriesInput> productCategoriesInputList;
}
