package ru.project1.service;

import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.project1.Models.Book;
import ru.project1.Models.Person;
import ru.project1.repository.BookRepository;
import ru.project1.repository.PersonRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
public class BookService {
    private final PersonRepository personRepository;
    private final BookRepository bookRepository;
    public BookService(PersonRepository personRepository, BookRepository bookRepository) {
        this.personRepository = personRepository;
        this.bookRepository = bookRepository;
    }

    @Transactional
    public void setBook(int id_book,Integer id_person){
        val book = bookRepository.findById(id_book).orElse(null);
        assert book != null;
        if(id_person==null){
            book.setCreatedAt(null);
            book.setOwner(null);
        }
        else {
            val person = personRepository.findById(id_person).orElse(null);
            book.setCreatedAt(new Date());
            book.setOwner(person);
        }
        bookRepository.save(book);
    }
    public List<Person> findAllPeoples(){
        return personRepository.findAll();
    }
    public Integer getIdOwner(int id){
         Book book=bookRepository.findById(id).orElse(null);
        assert book != null;
        if(book.getOwner()!=null) {
            return book.getOwner().getId();
        }
         return null;
    }
    public List<Book> findByTitleStartingWith(String title){
       return bookRepository.findByTitleStartingWith(title);
    }
    public Page<Book> indexOfEach(Integer offset, Integer limit){
        return bookRepository.findAll(PageRequest.of(offset,limit,Sort.by(Sort.Direction.DESC,"year")));
    }
    public List<Book> indexs(){
       return bookRepository.findAll(Sort.by(Sort.Direction.DESC,"year"));
    }
    public Page<Book> index(Integer offset,Integer limit){
        return bookRepository.findAll(PageRequest.of(offset,limit));
    }
    public String getFIO(int id){
       Person person= personRepository.findById(getIdOwner(id)).orElse(null);
        assert person != null;
        return person.getFIO();
    }
    public List<Book> index(){
        return bookRepository.findAll();
    }
    public Optional<Book> findById(int id){
        return bookRepository.findById(id);
    }
    @Transactional
    public void save(Book book){
        bookRepository.save(book);
    }
    @Transactional
    public void update(int id,Book updatedBook){
    updatedBook.setId_book(id);
    bookRepository.save(updatedBook);
    }
    @Transactional
    public void delete(int id){
        bookRepository.deleteById(id);
    }
}
