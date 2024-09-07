package by.eugene.library.controllers;

import by.eugene.library.model.Book;
import by.eugene.library.model.Person;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.client.HttpServerErrorException;

public interface BooksController {
    String getAllBooks(Model model);

    String getOneBook(Integer id, Model model);
    String getNewFormToCreateBook(@ModelAttribute("book") Book book);

    String createNewBook(@ModelAttribute("book") Book book, BindingResult bindingResult);
    String editBook(Integer id, Model model);

    String updateBook(@ModelAttribute("book") Book book, BindingResult bindingResult, Integer id);
    String deleteBook(Integer id);

    String releaseBook(Integer id);
    String assignBook(int id, @ModelAttribute("personToAssign") Person personToAssign);
}
