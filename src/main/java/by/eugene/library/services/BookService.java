package by.eugene.library.services;

import by.eugene.library.model.Book;
import by.eugene.library.model.Person;

import java.util.List;

public interface BookService {
    List<Book> findAll();
    Book findById(Integer id);
    List<Book> getBooksByOwnerId(Integer id);

    void save(Book book);
    void update(Integer id, Book book);

    void delete(Integer id);

    void releaseBook(Integer id);
    void assignBook(Integer id, Person personToAssign);
}
