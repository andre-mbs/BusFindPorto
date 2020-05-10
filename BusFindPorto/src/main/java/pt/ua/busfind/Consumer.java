package pt.ua.busfind;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
}
