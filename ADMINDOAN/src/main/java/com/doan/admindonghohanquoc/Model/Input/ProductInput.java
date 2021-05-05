package com.doan.admindonghohanquoc.Model.Input;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class ProductInput {
    private String productname;
    private String image;
    private Integer price;
    private String description;
    private Integer brandid;
    private String createdby;
    private Integer categoryid;
    private List<ProductAtributeInput> productAtributeInputs;
}
