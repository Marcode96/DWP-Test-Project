package bpdts;

// All the test are passed

import bpdts.api.LondonerController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DWPApiApplicationTest {

    @Autowired
    private LondonerController controller;

    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
    }

}