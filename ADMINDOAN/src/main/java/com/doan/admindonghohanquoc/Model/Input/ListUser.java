package com.doan.admindonghohanquoc.Model.Input;

import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data
public class ListUser {
    @ApiParam(value = "Page Number", required = true, example = "1")
    private Integer pageNumber;

    @ApiParam(value = "Page Size", required = true, example = "10")
    private Integer pageSize;

    @ApiParam(value = "Key search", required = false, example = "")
    private String search;
}
