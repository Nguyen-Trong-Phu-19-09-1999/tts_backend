package com.mycompany.service.itf;


import com.mycompany.dto.ProductDto;
import org.springframework.http.ResponseEntity;

public interface IProductService extends IGenerateService<ProductDto> {
    ResponseEntity<?> findProductByCategory (Long id) ;

}
