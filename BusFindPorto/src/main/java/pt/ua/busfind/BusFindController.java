package pt.ua.busfind;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author esP13
 */
@Controller
public class BusFindController {
    
    @Autowired
    private BusRepository br;
    
    @RequestMapping("busList")
    public String busList(Model model) {
        System.out.println("\nFrom the demo controller: busList\n");
        
        Iterable<Bus> busList = br.findAll();
        model.addAttribute("busList", busList);
        
        return "dashboard";
    }
    
    @RequestMapping("maps")
    public String maps(Model model) {
        System.out.println("\nFrom the demo controller: maps\n");
        
        return "maps";
    }
}
