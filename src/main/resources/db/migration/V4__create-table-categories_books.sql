CREATE TABLE categories_books(
    book_id BIGINT NOT NULL,
    category_id BIGINT NOT NULL,
    PRIMARY KEY (book_id, category_id),
    CONSTRAINT fk_categories_books_book
    FOREIGN KEY (book_id) REFERENCES books(id) ON DELETE CASCADE,
    CONSTRAINT fk_categories_books_category
    FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE CASCADE
);