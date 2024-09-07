package by.eugene.library.services;

import by.eugene.library.model.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {
    List<Person> findAll();
    Person findByID(Integer id);
    Person getBookOwner(Integer id);

    void save(Person person);
    void update(Integer id, Person person);

    void delete(Integer id);
}
