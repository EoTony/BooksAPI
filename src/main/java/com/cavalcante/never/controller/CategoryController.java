package com.cavalcante.never.controller;

import com.cavalcante.never.model.categories.CategoryRequestDTO;
import com.cavalcante.never.model.categories.CategoryResponseDTO;
import com.cavalcante.never.model.page.PageResponse;
import com.cavalcante.never.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public PageResponse<CategoryResponseDTO> findAll(@PageableDefault(size = 10,sort = "id")Pageable pageable){
        Page<CategoryResponseDTO> page = categoryService.findAll(pageable);
        return new PageResponse<CategoryResponseDTO>(page.getContent(),page.getNumber(),page.getSize(),page.getTotalElements(),page.getTotalPages());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> findById(@PathVariable Long id){
        CategoryResponseDTO categoryResponseDTO = categoryService.findById(id);
        return ResponseEntity.ok(categoryResponseDTO);
    }

    @PostMapping
    public ResponseEntity<CategoryResponseDTO> insert(@RequestBody @Valid CategoryRequestDTO categoryRequestDTO){
        CategoryResponseDTO category = categoryService.create(categoryRequestDTO);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(category.id())
                .toUri();
        return ResponseEntity.created(location).body(category);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> update(@RequestBody @Valid CategoryRequestDTO categoryRequestDTO,@PathVariable Long id){
        CategoryResponseDTO category = categoryService.update(categoryRequestDTO,id);

        return ResponseEntity.ok(category);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        categoryService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
