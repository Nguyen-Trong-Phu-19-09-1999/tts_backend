package com.mycompany.dto;

import com.mycompany.model.Product;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BillDto {
    private Long id ;
    private String name ;
    private String phoneNumber ;
    private Double price ;
    private LocalDateTime date ;
    private Double quantity  ;
    private String total ;
    private Product productId ;
    private String productName ;
}
