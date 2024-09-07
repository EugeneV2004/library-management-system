package by.eugene.library.dao;

import by.eugene.library.model.Book;
import by.eugene.library.model.Person;

import java.util.List;
import java.util.Optional;

public interface PersonDAO {
    List<Person> findAll();
    Optional<Person> findByID(Integer id);

    void save(Person person);
    void update(Integer id, Person person);

    void delete(Integer id);

    Optional<Person> getBookOwner(Integer id);
}
