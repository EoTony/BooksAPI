package com.cavalcante.never.model.categories;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoryRequestDTO(@NotBlank @Size(max = 50) String name) {
}
