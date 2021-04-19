package com.doan.admindonghohanquoc.Model.Input;

import lombok.Data;

@Data
public class CategoryInput {
    private String name;
    private String decription;
    private Integer status;
    private String createdby;
}
