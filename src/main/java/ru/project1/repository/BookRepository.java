package ru.project1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.project1.Models.Book;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {

     List<Book> findByTitleStartingWith(String title);
}
