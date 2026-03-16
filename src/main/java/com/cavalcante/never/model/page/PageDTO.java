package com.cavalcante.never.model.page;

import java.util.List;

public record PageDTO<T>(
        List<T> list,
        int page,
        int size,
        long totalItems,
        int totalPages
) {
}
