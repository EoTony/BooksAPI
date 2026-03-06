package com.cavalcante.never.model.author;

import com.cavalcante.never.model.book.Book;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String description;
    private LocalDate birthdate;
    private String nacionality;

    @OneToMany(mappedBy = "author")
    private Set<Book> books = new HashSet<>();

}
