package by.eugene.library.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private Integer id;
    @NotBlank(message = "Name should not be empty")
    @Size(min = 2, max = 50, message = "Name should be between 2 and 50 characters")
    private String name;
    @NotBlank(message = "Surname should not be empty")
    @Size(min = 2, max = 50, message = "Surname should be between 2 and 50 characters")
    private String surname;
    @NotBlank(message = "Patronymic should not be empty")
    @Size(min = 2, max = 50, message = "Patronymic should be between 2 and 50 characters")
    private String patronymic;
}
