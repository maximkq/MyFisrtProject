package ru.project1.Models;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "person2")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "fio")
    @NotEmpty(message = "Фамилия не может быть пустой")
    @Size(min = 10, max = 60, message = "В поле фамилия должно быть минимум 10 символов")
    private String FIO;
    @Column(name = "date_of_birth")
    @Min(value = 1920, message = "Дата рождения не может быть меньше 1920")
    @Max(value = 2020,message = "Дата рождения не может быть больше 2020")
    private int date_of_birth;
    @OneToMany(mappedBy = "owner")
    private List<Book> bookList;
    public Person() {
    }

    public Person(String FIO, int date_of_birth) {
        this.FIO = FIO;
        this.date_of_birth = date_of_birth;
    }
}
