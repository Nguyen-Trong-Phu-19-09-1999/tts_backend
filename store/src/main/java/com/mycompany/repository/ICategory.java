package com.mycompany.repository;

import com.mycompany.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICategory extends JpaRepository<Category, Long> {
    Category findCategoryById(Long aLong);
}
