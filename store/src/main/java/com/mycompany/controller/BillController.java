package com.mycompany.controller;

import com.mycompany.dto.BillDto;
import com.mycompany.model.Bill;
import com.mycompany.service.DI.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/bills")

public class BillController {
    @Autowired
    private BillService billService;

    @GetMapping
    public Iterable findAll() {
        return billService.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity findOne(@PathVariable Long id) {
        return billService.findById(id);
    }
    @DeleteMapping("/{idPrd}")
    public ResponseEntity delete(@PathVariable("idPrd") Long id) {
            return billService.delete(id);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody BillDto bill) {
        bill.setDate(LocalDateTime.now());
        return billService.save(bill);
    }
}
