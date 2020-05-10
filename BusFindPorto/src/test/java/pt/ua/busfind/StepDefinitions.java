package pt.ua.busfind;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 *
 * @author esP13
 */
public class StepDefinitions {
    @Given("The bus number is {int}")
    public void the_bus_number_is(int busNumber) {
        System.out.format("Bus: %n\n", busNumber);
    }
    
    @When("The timestamp is {int}")
    public void the_timestamp_is(int ts) {
        System.out.format("Timestamp: %n\n", ts);
    }
    
    @Then("The bus longitude is {int}")
    public void the_longitude_is(int lon) {
        System.out.format("Longitude: %n\n", lon);
    }
    
    @And("The bus latitude is {int}")
    public void the_latitude_is(int lat) {
        System.out.format("Latitude: %n\n", lat);
    }
}
