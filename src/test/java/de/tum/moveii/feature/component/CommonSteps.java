package de.tum.moveii.feature.component;

import cucumber.api.java.Before;
import de.tum.moveii.ops.logbook.Application;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

/**
 * Created by Alexandru Obada on 05/03/17.
 */
@ContextConfiguration
@SpringBootTest(classes = Application.class, webEnvironment = RANDOM_PORT)
public class CommonSteps {

    @Before
    public void setUp() {
    }
}
