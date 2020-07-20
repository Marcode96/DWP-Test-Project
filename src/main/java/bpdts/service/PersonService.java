package bpdts.service;

// Implement the service layer for the Person model

import bpdts.model.Person;
import bpdts.model.PersonList;
import bpdts.model.Position;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.comparingDouble;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

@Slf4j
@Service
public class PersonService {
    @Autowired
    PositionService positionService;

    public static final double DISTANCE_MILES = 50.0;
    public static final String BASE_URL = "https://bpdts-test-app.herokuapp.com/";
    private static final Logger logger = LoggerFactory.getLogger(PersonService.class);

    public static PersonList getAllPeopleList() {
        return getPersonList("people");
    }

    public static PersonList getLondonerList() {
        return getPersonList("city/Londoner");
    }

    private static PersonList getPersonList(String api) {
        String usersURL = BASE_URL + api;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Person>> peopleResponse = restTemplate.exchange(usersURL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Person>>() {
                });

        PersonList personList = new PersonList(peopleResponse.getBody());

        logger.info("From the api \"{}\", returning a list of {} users", personList.size(), api);
        return personList;
    }

    public PersonList nearLondon(List<Person> people) {
        return nearLondon(new PersonList(people));
    }

    public PersonList nearLondon(PersonList personList) {

        PersonList filteredPeople = new PersonList();
        filteredPeople.setPeople(
                personList.getPeople().stream()
                        .filter(person -> positionService.milesBetween(
                                positionService.LONDON, new Position(person.getLatitude(), person.getLongitude())
                        ) <= DISTANCE_MILES)
                        .collect(Collectors.toList())
        );
        return filteredPeople;
    }

    public List<Person> getPeopleInOrNearLondon() {
        PersonList people = getAllPeopleList();
        PersonList usersNearLondon = nearLondon(people);
        PersonList londoner = getLondonerList();

        List<Person> mergedPeople = Stream.of(londoner.getPeople(), usersNearLondon.getPeople())
                .flatMap(Collection::stream)
                .collect(collectingAndThen(
                        toCollection(() -> new TreeSet<>(comparingDouble(Person::getId))),
                        ArrayList::new));

        return mergedPeople;
    }
}
