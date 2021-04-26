package com.doan.admindonghohanquoc.Model.Input;

import com.doan.admindonghohanquoc.Model.Entity.BrandEntity;
import com.doan.admindonghohanquoc.Model.Entity.CategoriesEntity;
import lombok.Data;

import java.util.List;

@Data
public class ProductInput {
    private String productname;
    private String image;
    private Integer price;
    private String description;
    private Integer brandid;
    private Integer quantitysold;
    private Integer quantityremaining;
    private String createdby;
    private Integer categoryid;
    private List<ProductAtributeInput> productAtributeInputList;
    private List<ProductCategoriesInput> productCategoriesInputList;
}
