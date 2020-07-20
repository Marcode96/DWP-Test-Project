package bpdts.service;

import bpdts.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonServiceTest {
    @Autowired
    PersonService personService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getUsersInOrNearLondon() {
        List<Person> people = personService.getPeopleInOrNearLondon();
        int expectedUserCount = 9; // There are 9 londoner in the database
        assertEquals(expectedUserCount, people.size());
    }

}