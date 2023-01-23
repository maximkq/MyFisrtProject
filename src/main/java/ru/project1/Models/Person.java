package ru.project1.Models;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
@Getter
@Setter
public class Person {
    private int id;

    @NotEmpty(message = "Фамилия не может быть пустой")
    @Size(min = 10, max = 60, message = "В поле фамилия должно быть минимум 10 символов")
    private String FIO;
    @Min(value = 1920, message = "Дата рождения не может быть меньше 1920")
    @Max(value = 2020,message = "Дата рождения не может быть больше 2020")
    private int date_of_birth;



}
