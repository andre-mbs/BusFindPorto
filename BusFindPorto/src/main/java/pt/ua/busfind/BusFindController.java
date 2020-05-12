package pt.ua.busfind;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
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
        System.out.println("\nFrom the demo controller: busList\n");

        Iterable<Bus> busList = br.findAll();
        model.addAttribute("busList", busList);

        Set<String> ids = getNodeIds(busList);
        model.addAttribute("ids", ids);

        return "dashboard";
    }

    @PostMapping({"/", "busList"})
    public String busList(Model model, @RequestParam(name = "nodeID", required = false, defaultValue = "all") String nodeID) {
        System.out.println("\nFrom the demo controller: busList\n");

        Iterable<Bus> busList = br.findAll();

        if (nodeID.equals("all")) {
            model.addAttribute("busList", busList);
        } else {
            List<Bus> busListFiltered = getBusByNodeId(busList, nodeID);
            model.addAttribute("busList", busListFiltered);
        }

        Set<String> ids = getNodeIds(busList);
        model.addAttribute("ids", ids);

        return "dashboard";
    }

    @RequestMapping("maps")
    public String maps(Model model) {
        System.out.println("\nFrom the demo controller: maps\n");

        Iterable<Bus> busList = br.findAll();
        Set<String> ids = getNodeIds(busList);
        model.addAttribute("ids", ids);

        return "maps";
    }

    @PostMapping("maps")
    public String maps(Model model, @RequestParam(name = "nodeID", required = false, defaultValue = "all") String nodeID) {
        System.out.println("\nFrom the demo controller: maps\n" + nodeID);

        Iterable<Bus> busList = br.findAll();
        Set<String> ids = getNodeIds(busList);
        model.addAttribute("ids", ids);

        ObjectMapper mapper = new ObjectMapper();

        if (nodeID.equals("all")) {

        } else {
            try {
                List<Bus> busListFiltered = getBusByNodeId(busList, nodeID);
                String jsonBusList = mapper.writeValueAsString(busListFiltered);
                System.out.println(jsonBusList);
                model.addAttribute("jsonBusList", jsonBusList);
            } catch (JsonProcessingException ex) {
                Logger.getLogger(BusFindController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        return "maps";
    }

    private Set<String> getNodeIds(Iterable<Bus> busList) {
        Set<String> ids = new HashSet<>();
        for (Bus b : busList) {
            ids.add(b.getNode_id());
        }
        return ids;
    }

    private List<Bus> getBusByNodeId(Iterable<Bus> busList, String nodeId) {
        List<Bus> busListFiltered = new ArrayList<>();
        for (Bus b : busList) {
            if (b.getNode_id().equals(nodeId)) {
                busListFiltered.add(b);
            }
        }

        return busListFiltered;
    }
}
