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
    @Given("the bus number is {string}")
    public void the_bus_number_is(String string) {
        // Write code here that turns the phrase above into concrete actions
        // throw new io.cucumber.java.PendingException();
        System.out.println("bus: " + string);
    }

    @When("the timestamp is {string}")
    public void the_timestamp_is(String string) {
        // Write code here that turns the phrase above into concrete actions
        // throw new io.cucumber.java.PendingException();
        System.out.println("timestamp: " + string);

    }

    @Then("the bus longitude is {double}")
    public void the_bus_longitude_is(Double double1) {
        // Write code here that turns the phrase above into concrete actions
        // throw new io.cucumber.java.PendingException();
        System.out.println("long: " + double1);

    }

    @And("the bus latitude is {double}")
    public void the_bus_latitude_is(Double double1) {
        // Write code here that turns the phrase above into concrete actions
        // throw new io.cucumber.java.PendingException();
        System.out.println("lat: " + double1);

    }
}
