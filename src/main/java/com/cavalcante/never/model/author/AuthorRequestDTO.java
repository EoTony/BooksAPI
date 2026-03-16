package com.cavalcante.never.model.author;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record AuthorRequestDTO(
        @NotBlank @Size(max = 100) String name,
        @NotBlank @Email String email,
        @Size(max = 500) String description,
        @NotNull LocalDate birthdate,
        String nacionality) {
}