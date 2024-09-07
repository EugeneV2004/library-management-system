package by.eugene.library.dao.impl;

import by.eugene.library.dao.PersonDAO;
import by.eugene.library.model.Book;
import by.eugene.library.model.Person;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAOImpl implements PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    public PersonDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Person> findAll() {
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }

    @Override
    public Optional<Person> findByID(Integer id) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new Integer[]{id}, new BeanPropertyRowMapper(Person.class))
                .stream().findAny();
    }

    @Override
    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO Person(name, surname, patronymic, year_of_birth) VALUES (?, ?, ?, ?)",
                person.getName(),
                person.getSurname(),
                person.getPatronymic(),
                person.getYearOfBirth());
    }

    @Override
    public void update(Integer id, Person updatedPerson) {
         jdbcTemplate.update("UPDATE Person SET name=?, surname=?, patronymic=?, year_of_birth=? WHERE id=?",
                updatedPerson.getName(),
                updatedPerson.getSurname(),
                updatedPerson.getPatronymic(),
                updatedPerson.getYearOfBirth(),
                id);
    }

    @Override
    public void delete(Integer id) {
        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
    }

    @Override
    public Optional<Person> getBookOwner(Integer id) {
        return jdbcTemplate.query("SELECT Person.* FROM Book JOIN Person ON Book.owner_id = Person.id " +
                        "WHERE Book.id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny();
    }
}
