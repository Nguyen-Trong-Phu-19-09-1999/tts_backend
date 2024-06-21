package com.mycompany.model;

import lombok.Data;

import jakarta.persistence.*;

@Entity
@Data
public class Users {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id ;
    private String username ;
    private String password;
    private int status;
}
