package com.mycompany.repository;

import com.mycompany.model.Bill;
import com.mycompany.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBill extends JpaRepository<Bill, Long> {
    Bill findBillById(Long id);
}
