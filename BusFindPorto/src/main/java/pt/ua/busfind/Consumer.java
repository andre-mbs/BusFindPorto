package pt.ua.busfind;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import io.cucumber.core.gherkin.vintage.internal.gherkin.deps.com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 *
 * @author esP13
 */
@Service
public class Consumer {
    
    @Autowired
    private BusRepository br;
    
    ObjectMapper mapper = new ObjectMapper();
    
    @KafkaListener(topics = "topic1", groupId = "group_id")
    public void consume(String message){
        try {            
            Bus b = mapper.readValue(message, Bus.class);
            System.out.println("Data received from broker");
            
            br.save(b);
            System.out.println("Data saved to DB");
            
        } catch (IOException ex) {
            Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }


    @KafkaListener(topics = "esp13_tp1", groupId = "esp13")
    public void topic(String message) throws JsonProcessingException, JSONException {

        JSONObject jsonObject = new JSONObject(message);
        Gson gson = new Gson();
        Bus b = gson.fromJson(jsonObject.toString(), Bus.class);
        System.out.println("Received Message: " + jsonObject.toString());

    }
}
