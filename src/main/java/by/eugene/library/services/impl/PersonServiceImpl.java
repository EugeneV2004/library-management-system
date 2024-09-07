package by.eugene.library.services.impl;

import by.eugene.library.dao.impl.PersonDAOImpl;
import by.eugene.library.exceptions.BookNotFoundException;
import by.eugene.library.exceptions.PersonNotFoundException;
import by.eugene.library.model.Person;
import by.eugene.library.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonServiceImpl implements PersonService {

    private final PersonDAOImpl personDAO;

    @Autowired
    public PersonServiceImpl(PersonDAOImpl personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public List<Person> findAll() {
        return personDAO.findAll();
    }

    @Override
    public Person findByID(Integer id) {
        return personDAO.findByID(id)
                .orElseThrow(() -> new PersonNotFoundException("Person not found"));
    }

    @Override
    public Person getBookOwner(Integer id) {
        return personDAO.getBookOwner(id)
                .orElseThrow(() -> new BookNotFoundException("BookOwner not found"));
    }

    @Override
    public void save(Person person) {
        personDAO.save(person);
    }

    @Override
    public void update(Integer id, Person person) {
        personDAO.update(id, person);
    }

    @Override
    public void delete(Integer id) {
        personDAO.delete(id);
    }
}
