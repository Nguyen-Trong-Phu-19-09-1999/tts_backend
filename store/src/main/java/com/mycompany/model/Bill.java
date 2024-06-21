package com.mycompany.model;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Bill {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id ;
    private String name ;
    private String phoneNumber ;
    private Double price ;
    private LocalDateTime date ;
    private Double quantity  ;
    private String total ;
    @ManyToOne
    @JoinColumn(name = "productId")
    private Product productId ;
    private String productName ;
}
