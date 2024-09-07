package by.eugene.library.services.impl;

import by.eugene.library.dao.impl.BookDAOImpl;
import by.eugene.library.exceptions.BookNotFoundException;
import by.eugene.library.model.Book;
import by.eugene.library.model.Person;
import by.eugene.library.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookServiceImpl implements BookService {

    private final BookDAOImpl bookDAO;

    @Autowired
    public BookServiceImpl(BookDAOImpl bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Override
    public List<Book> findAll() {
        return bookDAO.findAll();
    }

    @Override
    public Book findById(Integer id) {
        return bookDAO.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found"));
    }

    @Override
    public List<Book> getBooksByOwnerId(Integer id) {
        return  bookDAO.getBooksByOwnerId(id);
    }

    @Override
    public void save(Book book) {
        bookDAO.save(book);
    }

    @Override
    public void update(Integer id, Book book) {
        bookDAO.update(id, book);
    }

    @Override
    public void delete(Integer id) {
        bookDAO.delete(id);
    }

    @Override
    public void releaseBook(Integer id) {
        bookDAO.releaseBook(id);
    }

    @Override
    public void assignBook(Integer id, Person personToAssign) {
        bookDAO.assignBook(id, personToAssign);
    }
}
