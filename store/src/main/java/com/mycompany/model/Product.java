package com.mycompany.model;

import lombok.Data;
import lombok.Getter;

import jakarta.persistence.*;


@Getter
@Entity
@Data
public class Product {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id ;
    private String name ;
    private Double quantity  ;
    private String price ;
    private int status;
    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category categoryId ;
}
