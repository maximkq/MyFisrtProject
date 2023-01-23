package ru.project1.Models;



import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

@Getter
@Setter
public class Book {

    private int id_book;
    private String id_person;
    @NotEmpty(message = "У книги должно быть название!!")
    private String title;
    @NotEmpty(message = "У книги должнен быть автор!")
    private String author;
    @Max(value = 2022, message = "Книга не может быть написана позже 2022 года")
    private int year;

    public String isEmpty(Book book){
        if(Objects.equals(book.getId_person(), null))
            return "Эта книга свободна.Кому назначить её?";
        return "Книга сейчас у:";
    }

}
