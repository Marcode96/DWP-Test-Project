package bpdts.api;

// Implement the API/ Controller Layer

import bpdts.model.Person;
import bpdts.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/bpdts")
public class LondonerController {

        @Autowired
        PersonService personService;

        @GetMapping(value = "/londoner")
        public List<Person> getLondoner() {

            List<Person> londoner = personService.getPeopleInOrNearLondon();
            log.info("Returning {} people in London or near it", londoner.size());

            return londoner;
        }

}
