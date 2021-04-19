package com.doan.admindonghohanquoc.Model.Input;

import lombok.Data;

@Data
public class CategoryUpdateInput {
    private Integer id;
    private String name;
    private String description;
    private Integer status;
    private String updatedby;
}
