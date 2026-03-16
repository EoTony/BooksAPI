package com.cavalcante.never.repositories;

import com.cavalcante.never.model.categories.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    boolean ExistsByName(String name);
}
