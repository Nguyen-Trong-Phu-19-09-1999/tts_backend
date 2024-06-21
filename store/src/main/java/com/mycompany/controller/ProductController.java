package com.mycompany.controller;

import com.mycompany.dto.ProductDto;
import com.mycompany.model.Product;
import com.mycompany.service.itf.ICategoryService;
import com.mycompany.service.itf.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/products")

public class ProductController {
    @Autowired
    private IProductService productService ;
    @Autowired
    private ICategoryService categoryService ;

    @GetMapping
    public Iterable findAll() {
        return productService.findAll();
    }


    @GetMapping("/category/{id}")
    public ResponseEntity findAllByCategory(@PathVariable Long id) {
        return productService.findProductByCategory(id);
    }
    @GetMapping("/{id}")
    public ResponseEntity findOne(@PathVariable Long id) {
        return productService.findById(id);
    }
    @DeleteMapping("/{idPrd}")
    public ResponseEntity delete(@PathVariable("idPrd") Long id) {
        return productService.delete(id);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody ProductDto product) {
        return productService.save(product);
    }
}
