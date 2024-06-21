package com.mycompany.service.DI;


import com.mycompany.dto.CategoryDto;
import com.mycompany.model.Category;
import com.mycompany.model.Response;
import com.mycompany.repository.ICategory;
import com.mycompany.service.itf.ICategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private ICategory iCategory;

    @Override
    public List<CategoryDto> findAll() {
        List<CategoryDto> list = new ArrayList<>();
        List<Category> categories = iCategory.findAll();
        for (Category category : categories) {
            CategoryDto categoryDto = new CategoryDto();
            BeanUtils.copyProperties(category, categoryDto);
            list.add(categoryDto);
        }
        return list;
    }

    @Override
    public ResponseEntity findById(Long id) {
        CategoryDto categoryDto = new CategoryDto();
        Category category = iCategory.findCategoryById(id);
        if (category != null) {
            BeanUtils.copyProperties(category, categoryDto);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new Response("Ok", "Tim thanh cong", categoryDto)
            );
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new Response("flase", "Khong tim thay Id", categoryDto)
            );
        }
    }

    @Override
    public ResponseEntity save(CategoryDto categoryDto) {
        ModelMapper modelMapper = new ModelMapper();
        if (categoryDto != null) {
            Category category = modelMapper.map(categoryDto, Category.class);
            iCategory.save(category);
            CategoryDto categoryDto1 = modelMapper.map(category, CategoryDto.class);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new Response("OK", "Them thanh cong", categoryDto1)
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new Response("Flase", "Khong thanh cong", null)
            );
        }
    }

    @Override
    public ResponseEntity delete(Long id) {
        Category category = iCategory.findCategoryById(id);
        if (category != null && category.getStatus()==1){
            category.setStatus(0);
            iCategory.save(category);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new Response("OK", "Xoa thanh cong", category)
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new Response("flase", "K co id nay hoac da xoa", category)
            );
        }
    }
}
