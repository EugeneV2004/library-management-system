package by.eugene.library.model;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Book {
    private int id;
    @NotEmpty(message = "Title should be not empty")
    @Size(min = 1, max = 100, message = "Title should be between 1 and 100 characters")
    private String title;
    @NotEmpty(message = "Author should be not empty")
    @Size(min = 2, max = 50, message = "Author should be between 1 and 100 characters")
    private String author;

    public Book() {}

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }
}
