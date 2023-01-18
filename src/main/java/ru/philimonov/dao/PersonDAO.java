package ru.philimonov.dao;

import org.springframework.stereotype.Component;
import ru.philimonov.models.Person;

import java.util.ArrayList;
import java.util.List;

//извлечение людей из DB
//нахождение конкретного человека по ID
//добавление/удаление/обновление человека из списка

@Component
public class PersonDAO {
    private List<Person> people;
    private static int PEOPLE_COUNTER;

    {
        people = new ArrayList<>();
        people.add(new Person(++PEOPLE_COUNTER, "Frank"));
        people.add(new Person(++PEOPLE_COUNTER, "Helen"));
        people.add(new Person(++PEOPLE_COUNTER, "Richard"));
        people.add(new Person(++PEOPLE_COUNTER, "Toby"));
    }

    public List<Person> index() {
        return people;
    }

    public Person show(int id) {
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }
}
