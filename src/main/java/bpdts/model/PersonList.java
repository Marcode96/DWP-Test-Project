package bpdts.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// The logic needed to obtain the list of people
@Component
public class PersonList {
        private List<Person> people;

        public List<Person> getPeople() {
            return people;
        }

        public void setPeople(List<Person> users) {
            this.people = users;
        }

        public PersonList() {
            this.people = new ArrayList<>();
        }

        public PersonList(List<Person> people) {
            this.people = people;
        }

        @Override
        public String toString() {
            return people.stream().map(Object::toString).collect(Collectors.joining(", "));
        }

        public int size() {
            return people.size();
        }
}