package pt.ua.busfind;

import org.springframework.beans.factory.annotation.Autowired;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.sql.*;


/**
 *
 * @author esP13
 */
public class StepDefinitions {
    
    // variables
    private String bus_number;
    private String timestamp;
    private double lon;
    private double lat;


    @Given("the bus number is {string}")
    public void the_bus_number_is(String string) {
        // Write code here that turns the phrase above into concrete actions
        // throw new io.cucumber.java.PendingException();
        this.bus_number = string;
        System.out.println("bus: " + string);
    }

    @When("the timestamp is {string}")
    public void the_timestamp_is(String string) {
        // Write code here that turns the phrase above into concrete actions
        // throw new io.cucumber.java.PendingException();
        try{
            this.timestamp = string;


            Class.forName("com.mysql.jdbc.Driver");

            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://192.168.160.103:13306/ProjectDB", 
                "user", "example"
            );

            Statement statement = conn.createStatement();

            ResultSet rs = statement.executeQuery("select * from bus where node_id = '" + this.bus_number + 
                                                  "' and ts = '"+ this.timestamp + "'");



            while(rs.next()){
                System.out.println(rs.getString(1) + " | " + 
                                   rs.getString(2) + " | " + 
                                   rs.getString(3) + " | " + 
                                   rs.getString(4) + " | " + 
                                   rs.getString(5) + " | " +
                                   rs.getString(6) + " | " +
                                   rs.getString(7) + " | " +
                                   rs.getString(8) + " | " + 
                                   rs.getString(9)
                                    );


                this.lon = rs.getDouble(5);
                this.lat = rs.getDouble(3);
            }

            conn.close();


        }catch(Exception e){
            System.out.println(e);
        }


            /**
             * private long id;
        private String node_id;
        private int location_id;
        private double head;
        private double lon;
        private double lat; 
        private int speed;
        private String ts; //mudar depois
        private String write_time; //mudar tambem para date
            */

        System.out.println("timestamp: " + string);

    }

    @Then("the bus longitude is {double}")
    public void the_bus_longitude_is(Double double1) {
        // Write code here that turns the phrase above into concrete actions
        // throw new io.cucumber.java.PendingException();
       
        System.out.println(double1 + "    " +  this.lon);

        assert double1 == this.lon;
        
        System.out.println("long: " + double1);

    }

    @And("the bus latitude is {double}")
    public void the_bus_latitude_is(Double double1) {
        // Write code here that turns the phrase above into concrete actions
        // throw new io.cucumber.java.PendingException();
        System.out.println(double1 + "    " +  this.lat);
        assert double1 == this.lat;
        System.out.println("lat: " + double1);

    }
}
