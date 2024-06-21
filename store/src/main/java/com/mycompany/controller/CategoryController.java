package com.mycompany.controller;

import com.mycompany.dto.CategoryDto;
import com.mycompany.service.itf.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/categories")

public class CategoryController {
    @Autowired
    private ICategoryService categoryService ;

    @GetMapping
    public Iterable<CategoryDto> findAll() {
        return categoryService.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findAllClassroom(@PathVariable Long id) {
        return  categoryService.findById(id);
    }
    @PostMapping
    public ResponseEntity<?> save(@RequestBody CategoryDto category) {
        categoryService.save(category);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return categoryService.delete(id);
    }
}
