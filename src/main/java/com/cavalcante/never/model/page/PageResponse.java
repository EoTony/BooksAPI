package com.cavalcante.never.model.page;

import java.util.List;

public record PageResponse<T>(
        List<T> list,
        int page,
        int size,
        long totalItems,
        int totalPages
) {
}
