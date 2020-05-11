package pt.ua.busfind;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 *
 * @author esP13
 */
@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources/pt/ua/busfind", 
                             "src/test/java/pt/ua/busfind/"})
public class TestRunner {
    
}
