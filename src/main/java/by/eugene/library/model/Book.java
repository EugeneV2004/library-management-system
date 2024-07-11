package by.eugene.library.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    private Integer id;
    @NotBlank(message = "Title should be not empty")
    @Size(min = 1, max = 100, message = "Title should be between 1 and 100 characters")
    private String title;
    @NotBlank(message = "Author should be not empty")
    @Size(min = 2, max = 50, message = "Author should be between 1 and 100 characters")
    private String author;

    private Integer ownerId;
}
