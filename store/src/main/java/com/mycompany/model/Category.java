package com.mycompany.model;

import lombok.Data;

import jakarta.persistence.*;

@Entity
@Data
public class Category {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id ;
    private String name ;
    private String description ;
    private int status;
}
