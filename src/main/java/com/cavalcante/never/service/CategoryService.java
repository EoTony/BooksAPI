package com.cavalcante.never.service;

import com.cavalcante.never.model.author.AuthorRequestDTO;
import com.cavalcante.never.model.author.AuthorResponseDTO;
import com.cavalcante.never.model.categories.Category;
import com.cavalcante.never.model.categories.CategoryRequestDTO;
import com.cavalcante.never.model.categories.CategoryResponseDTO;
import com.cavalcante.never.repositories.CategoryRepository;
import com.cavalcante.never.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public Page<CategoryResponseDTO> findAll(Pageable pageable){
        return categoryRepository.findAll(pageable).map(this::toResponse);
    }

    @Transactional(readOnly = true)
    public CategoryResponseDTO findById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        return toResponse(category);
    }

    @Transactional
    public CategoryResponseDTO create(CategoryRequestDTO categoryRequestDTO){
        if(categoryRepository.existsByName(categoryRequestDTO.name())){
            throw new IllegalArgumentException("Categoria já existente");
        }
        Category category = new Category();
        category.setName(categoryRequestDTO.name());

        categoryRepository.save(category);

        return toResponse(category);
    }

    @Transactional
    public void deleteById(Long id){
        categoryRepository.deleteById(id);
    }

    @Transactional
    public CategoryResponseDTO update(CategoryRequestDTO categoryRequestDTO, Long id){
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));

        category.setName(categoryRequestDTO.name());

        categoryRepository.save(category);

        return toResponse(category);
    }

    private CategoryResponseDTO toResponse(Category category){
        return new CategoryResponseDTO(category.getId(),category.getName());
    }


}
