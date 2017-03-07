package de.tum.moveii.feature.component;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;


/**
 * Created by Alexandru Obada on 05/03/17.
 */
@RunWith(Cucumber.class)
@CucumberOptions(strict = true, tags = "@component-test",
        features = "classpath:feature",
        plugin = {"pretty", "html:target/reports"},
        glue = {"classpath:de.tum.moveii.feature.component", "classpath:de.tum.moveii.feature.common"})
public class ComponentTest {
}
