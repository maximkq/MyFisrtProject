package ru.project1.Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.project1.Models.Book;


import java.util.List;
@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM book", new BeanPropertyRowMapper<>(Book.class));
    }
    public void setBook(int id_book, Integer id_person){
        jdbcTemplate.update("UPDATE book SET id_person=? WHERE id_book=?", id_person,id_book);
    }

    public Book show(int id) {
        return jdbcTemplate.query("SELECT * FROM book WHERE id_book=?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);
    }
    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO book(title,author,year) VALUES(?,?,?)", book.getTitle(),book.getAuthor(),book.getYear());
    }
    public void update(int id, Book updateBook) {
        jdbcTemplate.update("UPDATE book SET title=?, author=?,year=? WHERE id_book=?", updateBook.getTitle(),
                updateBook.getAuthor(),updateBook.getYear(), id);
    }
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM book WHERE id_book=?", id);
    }
}
