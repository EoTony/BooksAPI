package com.cavalcante.never.model.author;

import com.cavalcante.never.model.book.Book;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "authors")

public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true)
    private String email;
    @Column(length = 500)
    private String description;
    @JsonFormat
    private LocalDate birthdate;

    private String nacionality;

    @OneToMany(mappedBy = "author")
    private Set<Book> books = new HashSet<>();
}
