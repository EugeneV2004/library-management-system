package by.eugene.library.controllers.impl;

import by.eugene.library.controllers.BooksController;
import by.eugene.library.model.Book;
import by.eugene.library.model.Person;
import by.eugene.library.services.BookService;
import by.eugene.library.services.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BooksControllerImpl implements BooksController {

    private final BookService bookService;
    private final PersonService personService;

    @Autowired
    public BooksControllerImpl(BookService bookService, PersonService personService) {
        this.bookService = bookService;
        this.personService = personService;
    }

    @Override
    @GetMapping()
    public String getAllBooks(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "books/allBooks";
    }

    @Override
    @GetMapping("/{id}")
    public String getOneBook(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("book", bookService.findById(id));

        Book book = bookService.findById(id);
        if (book.getOwnerId() == null) {
            model.addAttribute("people", personService.findAll());
            model.addAttribute("person", new Person());
        } else {
            model.addAttribute("owner", personService.findByID(book.getOwnerId()));
        }
        return "books/oneBook";
    }

    @Override
    @GetMapping("/new")
    public String getNewFormToCreateBook(@ModelAttribute("book") Book book) {
        return "books/newFormForBook";
    }

    @Override
    @PostMapping()
    public String createNewBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "books/newFormForBook";
        }

        bookService.save(book);
        return "redirect:/books";
    }

    @Override
    @GetMapping("/{id}/edit")
    public String editBook(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("book", bookService.findById(id));
        return "books/editBookForm";
    }

    @Override
    @PostMapping("/{id}/edit")
    public String updateBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
                             @PathVariable("id") Integer id) {
        if (bindingResult.hasErrors()) {
            return "books/editBookForm";
        }

        bookService.update(id, book);
        return "redirect:/books";
    }

    @Override
    @PostMapping("/{id}/delete")
    public String deleteBook(@PathVariable("id") Integer id) {
        bookService.delete(id);
        return "redirect:/books";
    }

    @Override
    @PostMapping("/{id}/release")
    public String releaseBook(@PathVariable("id") Integer id) {
        bookService.releaseBook(id);
        return "redirect:/books/" + id;
    }

    @Override
    @PostMapping("/{id}/assign")
    public String assignBook(@PathVariable("id") int id, @ModelAttribute("personToAssign") Person personToAssign) {
        bookService.assignBook(id, personToAssign);
        return "redirect:/books/" + id;
    }
}
