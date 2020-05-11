package pt.ua.busfind;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
        
        Set<String> ids = new HashSet<>();
        for (Bus b : busList) {
            ids.add(b.getNode_id());
        }
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
            List<Bus> busListFiltered = new ArrayList<>();
            for (Bus b : busList) {
                if(b.getNode_id().equals(nodeID))
                    busListFiltered.add(b);
            }
            model.addAttribute("busList", busListFiltered);
        }

        Set<String> ids = new HashSet<>();
        for (Bus b : busList) {
            ids.add(b.getNode_id());
        }
        model.addAttribute("ids", ids);

        return "dashboard";
    }
    
    @RequestMapping("maps")
    public String maps(Model model) {
        System.out.println("\nFrom the demo controller: maps\n");
        
        return "maps";
    }
}
