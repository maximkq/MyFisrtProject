package ru.project1.Models;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Person {
    private int id;

    @NotEmpty(message = "Фамилия не может быть пустой")
    @Size(min = 10, max = 60, message = "В поле фамилия должно быть минимум 10 символов")
    private String FIO;

    @Min(value = 1920, message = "Дата рождения не может быть меньше 1920")
    @Max(value = 2020,message = "Дата рождения не может быть больше 2020")
    private int age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFIO() {
        return FIO;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }
}
