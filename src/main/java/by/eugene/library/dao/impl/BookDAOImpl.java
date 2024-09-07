package by.eugene.library.dao.impl;

import by.eugene.library.dao.BookDAO;
import by.eugene.library.model.Book;
import by.eugene.library.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAOImpl implements BookDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Book> findAll() {
        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper(Book.class));
    }

    @Override
    public Optional<Book> findById(Integer id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper(Book.class))
                .stream().findAny();
    }

    @Override
    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO Book(title, author, year) VALUES (?, ?, ?)",
                book.getTitle(),
                book.getAuthor(),
                book.getYear());
    }

    @Override
    public void update(Integer id, Book updatedBook) {
        jdbcTemplate.update("UPDATE Book SET title=?, author=?, year=? WHERE id=?",
                updatedBook.getTitle(),
                updatedBook.getAuthor(),
                updatedBook.getYear(),
                id);
    }

    @Override
    public void delete(Integer id) {
        jdbcTemplate.update("DELETE FROM Book WHERE id=?", id);
    }

    @Override
    public void releaseBook(Integer id) {
        jdbcTemplate.update("UPDATE Book SET owner_id = NULL WHERE id=?", id);
    }

    @Override
    public void assignBook(Integer id, Person personToAssign) {
        jdbcTemplate.update("UPDATE Book SET owner_id=? WHERE id=?", personToAssign.getId(), id);
    }

    @Override
    public List<Book> getBooksByOwnerId(Integer id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE book.owner_id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class));
    }
}
