package ru.project1.Models;



import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class Book {
    private int book_id;
    private int people_id;
    @NotEmpty(message = "У книги должно быть название!!")
    private String title;
    @NotEmpty(message = "У книги должнен быть автор!")
    private String author;
    @Max(value = 2022,message="Книга не может быть написана позже 2022 года")
    private int year;


}
