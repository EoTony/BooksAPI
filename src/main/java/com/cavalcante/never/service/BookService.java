package com.cavalcante.never.service;

import com.cavalcante.never.model.author.Author;
import com.cavalcante.never.model.book.Book;
import com.cavalcante.never.model.book.BookRequestDTO;
import com.cavalcante.never.model.book.BookResponseDTO;
import com.cavalcante.never.model.categories.Category;
import com.cavalcante.never.repositories.AuthorRepository;
import com.cavalcante.never.repositories.BookRepository;
import com.cavalcante.never.repositories.CategoryRepository;
import com.cavalcante.never.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public Page<BookResponseDTO> findAll(Pageable pageable){
        return bookRepository.findAll(pageable).map(this::toResponse);
    }

    @Transactional(readOnly = true)
    public BookResponseDTO findById(Long id){
        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));

        return toResponse(book);
    }

    @Transactional
    public BookResponseDTO update(BookRequestDTO bookRequestDTO,Long id){

        Author author = authorRepository.findById(bookRequestDTO.authorId()).orElseThrow(() -> new ResourceNotFoundException(id));

        List<Category> updateList = categoryRepository.findAllById(bookRequestDTO.categoriesIds());


        if(updateList.size() != bookRequestDTO.categoriesIds().size()){
            throw new IllegalArgumentException("Uma ou mais categorias não existem");
        }
        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));

        book.setTitle(bookRequestDTO.title());
        book.setDescription(bookRequestDTO.description());
        book.setPublisher(bookRequestDTO.publisher());
        book.setPublidate(bookRequestDTO.publidate());
        book.setPrice(bookRequestDTO.price());
        book.setAuthor(author);
        book.getCategories().addAll(updateList);



        bookRepository.save(book);

        return toResponse(book);
    }


    @Transactional
    public BookResponseDTO insert(BookRequestDTO bookRequestDTO){

        Author author = authorRepository.findById(
                bookRequestDTO.authorId()).
                orElseThrow(() -> new ResourceNotFoundException(bookRequestDTO.authorId()));

        List<Category> categories =  categoryRepository.findAllById(bookRequestDTO.categoriesIds());

        if (categories.size() != bookRequestDTO.categoriesIds().size()){
            throw new IllegalArgumentException("Uma ou mais categorias não existem");
        }

        Book book = new Book();

        book.setTitle(bookRequestDTO.title());
        book.setDescription(bookRequestDTO.description());
        book.setPublisher(bookRequestDTO.publisher());
        book.setPublidate(bookRequestDTO.publidate());
        book.setPrice(bookRequestDTO.price());
        book.setAuthor(author);
        book.getCategories().addAll(categories);

        bookRepository.save(book);

        return toResponse(book);
    }

    @Transactional
    public void deleteById(Long id){
        bookRepository.deleteById(id);
    }

    private BookResponseDTO toResponse(Book book){
        return new BookResponseDTO(
                book.getId(),
                book.getTitle(),
                book.getDescription(),
                book.getPublisher(),
                book.getPublidate(),
                book.getPrice(),
                book.getAuthor().getId());
    }
}
