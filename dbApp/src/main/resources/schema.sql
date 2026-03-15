DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS authors;

CREATE TABLE authors
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    age  INT
);

CREATE TABLE books
(
    isbn      VARCHAR(255) PRIMARY KEY,
    title     VARCHAR(200),
    author_id INT,
    CONSTRAINT fk_author
        FOREIGN KEY (author_id)
            REFERENCES authors (id)
);