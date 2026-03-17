package com.cavalcante.never.controller;

import com.cavalcante.never.model.author.AuthorRequestDTO;
import com.cavalcante.never.model.author.AuthorResponseDTO;
import com.cavalcante.never.model.page.PageResponse;
import com.cavalcante.never.service.AuthorService;
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
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponseDTO> findById(@PathVariable Long id){
        AuthorResponseDTO authorResponseDTO = this.authorService.findById(id);
        return ResponseEntity.ok(authorResponseDTO);
    }

    @PostMapping()
    public ResponseEntity<AuthorResponseDTO> createAuthor(@RequestBody @Valid AuthorRequestDTO authorRequestDTO){
        AuthorResponseDTO author = authorService.insert(authorRequestDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(author.id())
                .toUri();

        return ResponseEntity.created(location).body(author);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        authorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorResponseDTO> update(@PathVariable Long id,@RequestBody @Valid AuthorRequestDTO authorRequestDTO){
        AuthorResponseDTO author = authorService.update(authorRequestDTO,id);
        return ResponseEntity.ok(author);
    }

    @GetMapping
    public PageResponse<AuthorResponseDTO> findAll(@PageableDefault(size = 5,sort = "id")Pageable pageable){
        Page<AuthorResponseDTO> page = authorService.findAll(pageable);
        return new PageResponse<AuthorResponseDTO>(page.getContent(),page.getNumber(),page.getSize(),page.getTotalElements(),page.getTotalPages());
    }

}
