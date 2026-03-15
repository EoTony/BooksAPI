package com.cavalcante.never.repositories;

import com.cavalcante.never.model.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long> {
}
