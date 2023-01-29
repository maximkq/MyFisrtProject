package ru.project1.Models;



import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "book")
public class Book {
    @Id
    @Column(name = "id_book")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_book;
    @ManyToOne
    @JoinColumn(name = "id_person" ,referencedColumnName = "id")
    private Person owner;
    @Column(name = "title")
    @NotEmpty(message = "У книги должно быть название!!")
    private String title;
    @Column(name = "author")
    @NotEmpty(message = "У книги должнен быть автор!")
    private String author;
    @Column(name = "year")
    @Max(value = 2022, message = "Книга не может быть написана позже 2022 года")
    private int year;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Transient
    private boolean expired=false;

    public Book() {
    }

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }
}
