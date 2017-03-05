package de.tum.moveii.feature.component;

import cucumber.api.java.Before;
import de.tum.moveii.ops.logbook.Application;
import de.tum.moveii.ops.logbook.log.service.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

/**
 * Created by Alexandru Obada on 05/03/17.
 */
@ContextConfiguration
@SpringBootTest(classes = Application.class, webEnvironment = RANDOM_PORT)
public class CommonSteps {

    @Autowired
    private LogRepository logRepository;

    @Before
    public void setUp() {
    }
}
