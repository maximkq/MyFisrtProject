package ru.project1.service;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.project1.Models.Book;
import ru.project1.Models.Person;
import ru.project1.repository.PersonRepository;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PersonService {
    private final PersonRepository personRepository;
    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    public List<Person> findAll(){
        return personRepository.findAll();
    }
    public Optional<Person> findById(int id){
        return personRepository.findById(id);
    }
    public List<Book> getBooksByIdPerson(Integer id){
        Optional<Person> person=personRepository.findById(id);
        if(person.isPresent()){
            Hibernate.initialize(person.get().getBookList());
            person.get().getBookList().forEach(book -> {
                long time=Math.abs(book.getCreatedAt().getTime()-new Date().getTime() );
                if(time>864000000)
                    book.setExpired(true);
            });
            return person.get().getBookList();
        }
        else{
            return Collections.emptyList();
        }
    }
    @Transactional
    public void save(Person person){
        personRepository.save(person);
    }
    @Transactional
    public void update(int id,Person updatedPerson){
        updatedPerson.setId(id);
       personRepository.save(updatedPerson);
    }

    @Transactional
    public void delete(int id){
        personRepository.deleteById(id);
    }

}
