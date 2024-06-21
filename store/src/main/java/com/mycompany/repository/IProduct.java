
package com.mycompany.repository;

import com.mycompany.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IProduct extends JpaRepository<Product, Long> {
    Optional<Product> findProductById (Long id);

    Optional<Product> findAllByName(String name);

    @Query(value = "SELECT * FROM product WHERE category_id = :id", nativeQuery = true)
    List<Product> findAllByCategoryId (@Param("id")Long id);

}
