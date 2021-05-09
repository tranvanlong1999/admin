package com.doan.admindonghohanquoc.Model.OutPut;
import lombok.Data;



@Data
public class ProductOutput {
    private Integer id;
    private String productname;
    private String description;
    private Integer brandid;
    private Integer categoryid;
    private String PriceStr;
    private int price;
    private String createAt;
    private Integer status;
    private String Image;
    private String brandname;
    private String categoryname;
}
