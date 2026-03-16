package com.cavalcante.never.model.book;

import java.math.BigDecimal;
import java.time.LocalDate;

public record BookResponseDTO(Long id,
                              String title,
                              String description,
                              String publisher,
                              LocalDate publidate,
                              BigDecimal price,
                              Long author_id) {
}
