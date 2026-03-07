package com.cavalcante.never.model.book;

import com.cavalcante.never.model.author.Author;
import com.cavalcante.never.model.categories.Category;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String publisher;
    private LocalDate publicationdate;
    private Double price;

    @ManyToMany
    @JoinTable(name = "categories_books",
    joinColumns = @JoinColumn(name = "book_id"),
    inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
}
