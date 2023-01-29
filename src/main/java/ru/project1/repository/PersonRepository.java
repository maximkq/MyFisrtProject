package ru.project1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.project1.Models.Person;
@Repository
public interface PersonRepository extends JpaRepository<Person,Integer> {
}
