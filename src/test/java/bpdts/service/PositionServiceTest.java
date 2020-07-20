package bpdts.service;

/* Test for the positions of Newcastle (outside the 50 miles range) and
Romford (inside the range)
*/
import bpdts.model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PositionServiceTest {

    @Autowired
    PositionService positionService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void milesLondonToNewcastle() {
        Position london = new Position(PositionService.LONDON_LATITUDE, PositionService.LONDON_LONGITUDE);
        Position newcastle = new Position(54.9666,-1.6);

        Double expectedDistance = 282.8;

        assertEquals(expectedDistance, positionService.milesBetween(london, newcastle), 0.1);
    }

    @Test
    void milesLondonToRomford() {
        Position london = new Position(PositionService.LONDON_LATITUDE, PositionService.LONDON_LONGITUDE);
        Position romford = new Position(51.5812, 0.1837);

        Double expectedDistance = 16.4;

        assertEquals(expectedDistance, positionService.milesBetween(london, romford), 0.1);
    }

    @Test
    void milesToKm() {
        assertEquals(1.60934, PositionService.milesToKm(1.0), 0.0001);
    }

    @Test
    void kmToMiles() {
        assertEquals(1.0, PositionService.kmToMiles(1.60934), 0.0001);
    }

}