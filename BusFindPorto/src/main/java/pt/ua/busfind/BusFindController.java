package pt.ua.busfind;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author esP13
 */
@Controller
public class BusFindController {

    @Autowired
    private BusRepository br;

    @RequestMapping({"/", "busList"})
    public String busList(Model model) {
        System.out.println("From the controller: busList");

        Iterable<Bus> busList = br.findAll();
        model.addAttribute("busList", busList);

        List<Integer> ids = getLocationIds(busList);
        model.addAttribute("ids", ids);

        return "dashboard";
    }

    @PostMapping({"/", "busList"})
    public String busList(Model model, @RequestParam(name = "nodeID", required = false, defaultValue = "all") String locationId) {
        System.out.println("\nFrom the demo controller: busList\n");

        Iterable<Bus> busList = br.findAll();

        if (locationId.equals("all")) {
            model.addAttribute("busList", busList);
        } else {
            List<Bus> busListFiltered = getBusByLocationId(busList, Integer.parseInt(locationId));
            model.addAttribute("busList", busListFiltered);
        }

        List<Integer> ids = getLocationIds(busList);
        model.addAttribute("ids", ids);

        return "dashboard";
    }

    @RequestMapping("maps")
    public String maps(Model model) {
        System.out.println("From the controller: maps");

        Iterable<Bus> busList = br.findAll();
        List<Integer> ids = getLocationIds(busList);
        model.addAttribute("ids", ids);

        return "maps";
    }

    @PostMapping("maps")
    public String maps(Model model, @RequestParam(name = "nodeID", required = false, defaultValue = "all") String locationId) {
        System.out.println("From the controller: maps");

        boolean allFag = false;
        String jsonBusList = "";
        
        Iterable<Bus> busList = br.findAll();
        List<Integer> ids = getLocationIds(busList);
        model.addAttribute("ids", ids);

        ObjectMapper mapper = new ObjectMapper();

        if (locationId.equals("all")) {
            try {
                allFag = true;
                jsonBusList = mapper.writeValueAsString(busList);
            } catch (JsonProcessingException ex) {
                Logger.getLogger(BusFindController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                List<Bus> busListFiltered = getBusByLocationId(busList, Integer.parseInt(locationId));
                jsonBusList = mapper.writeValueAsString(busListFiltered);  
            } catch (JsonProcessingException ex) {
                Logger.getLogger(BusFindController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        
        System.out.println(jsonBusList);
        model.addAttribute("jsonBusList", jsonBusList);
        model.addAttribute("allFlag", allFag);
        model.addAttribute("locationIdText", locationId);
        
        return "maps";
    }

    private List<Integer> getLocationIds(Iterable<Bus> busList) {
        Set<Integer> ids = new HashSet<>();
        for (Bus b : busList) {
            ids.add(b.getLocation_id());
        }
        
        List<Integer> idsList = new ArrayList<>(ids);
        Collections.sort(idsList);
        return idsList;
    }

    private List<Bus> getBusByLocationId(Iterable<Bus> busList, int locationId) {
        List<Bus> busListFiltered = new ArrayList<>();
        for (Bus b : busList) {
            if (b.getLocation_id() == locationId) {
                busListFiltered.add(b);
            }
        }

        return busListFiltered;
    }
}
