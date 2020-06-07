package pt.ua.busfind;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/logging-example")
public class elkControler {


    // declare the SLF4J logger
    private Logger logger = LoggerFactory.getLogger(elkControler.class);

    @GetMapping
    public ResponseEntity<String> get() {
        // log an info message
        logger.info("An info message");
        return ResponseEntity.ok("Hello world");

}
}
