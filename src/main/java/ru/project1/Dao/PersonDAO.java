package ru.project1.Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
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

    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM Person2 WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO Person2(fio,age_of_birth) VALUES(?,?)", person.getFIO(),person.getAge());
    }

    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE Person2 SET fio=?, age_of_birth=? WHERE id=?", updatedPerson.getFIO(),
                updatedPerson.getAge(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person2 WHERE id=?", id);
    }
}
