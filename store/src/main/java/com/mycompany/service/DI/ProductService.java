package com.mycompany.service.DI;


import com.mycompany.dto.ProductDto;
import com.mycompany.model.Product;
import com.mycompany.model.Response;
import com.mycompany.repository.IProduct;
import com.mycompany.service.itf.IProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {
    @Autowired
    private IProduct iProduct ;


    @Override
    public List<ProductDto> findAll() {
        List<ProductDto> list = new ArrayList<>();
        List<Product> products = iProduct.findAll();
        for (Product product: products
             ) {
            ProductDto productDto = new ProductDto();
            BeanUtils.copyProperties(product, productDto);
            list.add(productDto);
        }
        return list;
    }

    @Override
    public ResponseEntity findById(Long id) {
        ProductDto productDto = new ProductDto();
        Optional<Product>  product = iProduct.findProductById(id);
        if (product.isPresent()){
            BeanUtils.copyProperties(product,productDto);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new Response("Ok", "Tim thanh cong", productDto)
            );
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new Response("Flase", "Khong co Id nay", null)
            );
        }
    }

    @Override
    public ResponseEntity save(ProductDto productDto) {
        ModelMapper modelMapper = new ModelMapper();
        if (productDto != null && iProduct.findAllByName(productDto.getName()).isEmpty()){
            Product product = modelMapper.map(productDto, Product.class);
            iProduct.save(product);
            ProductDto productDto1 = modelMapper.map(product, ProductDto.class);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new Response("OK", "Them thanh cong", productDto1)
            );
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new Response("Flase", "Khong thanh cong", null)
            );
        }
    }

    @Override
    public ResponseEntity delete(Long id) {
        Optional<Product> product = iProduct.findProductById(id);
        if (product.isPresent()){
            product.get().setStatus(0);
            iProduct.save(product.get());
            return ResponseEntity.status(HttpStatus.OK).body(
                    new Response("OK", "Xoa thanh cong", product)
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new Response("flase", "K co id nay", product)
            );
        }

    }

    @Override
    public ResponseEntity<?> findProductByCategory(Long id) {
       List<Product> productList = iProduct.findAllByCategoryId(id);
       if (productList.size()!=0){
           List<ProductDto> productListDto = new ArrayList<>();
           for (Product product: productList) {
               ProductDto productDto = new ProductDto();
               BeanUtils.copyProperties(product,productDto);
               productListDto.add(productDto);
           }
           return ResponseEntity.status(HttpStatus.OK).body(
                   new Response("OK", "DS list", productListDto)
           );
       }
       else{
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                   new Response("flase", "K co DS list", null)
           );
       }
    }
}
