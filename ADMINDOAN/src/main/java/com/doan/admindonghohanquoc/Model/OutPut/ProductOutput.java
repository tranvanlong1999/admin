package com.doan.admindonghohanquoc.Model.OutPut;
import com.doan.admindonghohanquoc.Model.Entity.BrandEntity;
import com.doan.admindonghohanquoc.Model.Entity.CategoriesEntity;
import lombok.Data;

import java.util.Date;

@Data
public class ProductOutput {
    private Integer id;
    private String productname;
    private String description;
    private Integer brandid;
    private Integer categoryid;
    private String PriceStr;
    private int price;
    private Date createAt;
    private Integer status;
    private String Image;
    private String brandname;
    private String categoryname;
}
