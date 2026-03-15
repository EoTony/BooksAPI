package com.cavalcante.never.model.author;

import java.time.LocalDate;

public record AuthorResponseDTO(Long id,String name,String Email,String description, LocalDate birthdate,String nacionality) {
}
