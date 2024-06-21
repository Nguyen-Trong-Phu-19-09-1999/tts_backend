package com.mycompany.service.itf;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IGenerateService<E> {
    List<E> findAll();

    ResponseEntity findById(Long id);

    ResponseEntity save(E e);

    ResponseEntity delete(Long id);
}
