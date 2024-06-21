package com.mycompany.dto;

import lombok.Data;

@Data
public class ProductDto {
    private String name ;
    private Double quantity  ;
    private String price ;
    private int status;
}
