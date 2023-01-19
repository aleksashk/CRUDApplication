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
        people.add(new Person(++PEOPLE_COUNTER, "Frank", 54, "frank@gmail.com"));
        people.add(new Person(++PEOPLE_COUNTER, "Helen", 20, "helen@mail.ru"));
        people.add(new Person(++PEOPLE_COUNTER, "Richard", 24, "rich@gmail.com"));
        people.add(new Person(++PEOPLE_COUNTER, "Toby", 12, "ttt78@mail.ru"));
    }

    public List<Person> index() {
        return people;
    }

    public Person show(int id) {
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

    public void save(Person person) {
        person.setId(++PEOPLE_COUNTER);
        people.add(person);
    }

    public void update(int id, Person updatePerson) {
        Person personToBeUpdated = show(id);

        personToBeUpdated.setName(updatePerson.getName());
        personToBeUpdated.setAge(updatePerson.getAge());
        personToBeUpdated.setEmail(updatePerson.getEmail());
    }

    public void delete(int id) {
        people.removeIf(person -> person.getId() == id);
    }
}
