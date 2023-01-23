package ru.project1.Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.project1.Models.Book;
import ru.project1.Models.Person;

import java.util.List;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM Person2", new BeanPropertyRowMapper<>(Person.class));
    }
    public List<Book> isHaveBooks(int id){
        return jdbcTemplate.query("SELECT id_book, id_person, title, author, year FROM book JOIN person2 on person2.id = book.id_person WHERE id_person=?", new Object[]{id},new BeanPropertyRowMapper<>(Book.class));
    }
    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM Person2 WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO Person2(fio,date_of_birth) VALUES(?,?)", person.getFIO(),person.getDate_of_birth());
    }

    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE Person2 SET fio=?, date_of_birth=? WHERE id=?", updatedPerson.getFIO(),
                updatedPerson.getDate_of_birth(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person2 WHERE id=?", id);
    }
}
