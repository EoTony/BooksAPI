package com.cavalcante.never.controller;

import com.cavalcante.never.model.book.BookRequestDTO;
import com.cavalcante.never.model.book.BookResponseDTO;
import com.cavalcante.never.model.page.PageDTO;
import com.cavalcante.never.service.BookService;
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
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;



    @GetMapping
    public PageDTO<BookResponseDTO> findAll(@PageableDefault(size = 10,sort = "id")Pageable pageable){
        Page<BookResponseDTO> page = bookService.findAll(pageable);
        return new PageDTO<BookResponseDTO>(page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDTO> findById(@PathVariable Long id){
        BookResponseDTO bookResponseDTO = bookService.findById(id);
        return ResponseEntity.ok(bookResponseDTO);
    }

    @PostMapping
    public ResponseEntity<BookResponseDTO> insert(@RequestBody @Valid BookRequestDTO bookRequestDTO){
        BookResponseDTO book = bookService.insert(bookRequestDTO);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(book.id())
                .toUri();

        return ResponseEntity.created(location).body(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDTO> update(@RequestBody @Valid BookRequestDTO bookRequestDTO,@PathVariable Long id){
        BookResponseDTO book = bookService.update(bookRequestDTO,id);
        return ResponseEntity.ok(book);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        bookService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
