package ru.philimonov.dao;

import org.springframework.stereotype.Component;
import ru.philimonov.models.Person;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//извлечение людей из DB
//нахождение конкретного человека по ID
//добавление/удаление/обновление человека из списка

@Component
public class PersonDAO {
    private static int PEOPLE_COUNTER;

    private static final String URL = "jdbc:postgresql://localhost:5432/first_db";
        private static final String USERNAME = "postgres";
        private static final String PASSWORD = "password";

        private static Connection connection;

        static {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            try {
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    public List<Person> index() {
            List<Person> people = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String sgl = "select * from Person";
            ResultSet resultSet = statement.executeQuery(sgl);

            while (resultSet.next()){
                Person person = new Person();
                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setEmail(resultSet.getString("email"));
                person.setAge(resultSet.getInt("age"));

                people.add(person);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return people;
    }

//    public Person show(int id) {
//        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
//    }

    public void save(Person person) {
        try {
            Statement statement = connection.createStatement();
            String sgl = "INSERT INTO Person VALUES(" + 1 + ",'" + person.getName() + "'," + person.getAge() + ",'" + person.getEmail() + "')";
            statement.executeUpdate(sgl);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//    public void update(int id, Person updatePerson) {
//        Person personToBeUpdated = show(id);
//
//        personToBeUpdated.setName(updatePerson.getName());
//        personToBeUpdated.setAge(updatePerson.getAge());
//        personToBeUpdated.setEmail(updatePerson.getEmail());
//    }

//    public void delete(int id) {
//        people.removeIf(person -> person.getId() == id);
//    }
}
