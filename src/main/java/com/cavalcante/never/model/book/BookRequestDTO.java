package com.cavalcante.never.model.book;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

public record BookRequestDTO(
        @NotBlank @Size(max = 100) String title,
        @Size(max = 500) String description,
        @Size(max = 100) String publisher,
        @NotNull LocalDate publidate,
        @NotNull @DecimalMin("0.00") @Digits(integer = 10,fraction = 2)BigDecimal price,
        @NotNull Long authorId,
        Set<Long> categoriesIds
        ) {
}
