package com.cavalcante.never.model.author;

import java.time.LocalDate;

public record AuthorResponseDTO(Long id,
                                String name,
                                String email,
                                String description,
                                LocalDate birthdate,
                                String nacionality) {
}
