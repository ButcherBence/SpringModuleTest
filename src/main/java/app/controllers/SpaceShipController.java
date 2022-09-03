package app.controllers;

import app.models.SpaceShip;
import app.models.SpaceShipClass;
import app.services.SpaceShipService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class SpaceShipController {

    private SpaceShipService spaceShipService;

    public SpaceShipController(SpaceShipService spaceShipService) {
        this.spaceShipService = spaceShipService;
    }

    @GetMapping(value = {"/spaceship"})
    public String saveNewSpaceShip(Model model) {
        model.addAttribute("newShip", new SpaceShip());
        model.addAttribute("shipClasses", SpaceShipClass.values());

        return "spaceship_new";
    }

    @PostMapping("/spaceship")
    public String addSpaceShip(SpaceShip spaceShip){
        spaceShipService.saveSpaceShip(spaceShip);

        return "redirect:/spaceships";
    }

    @GetMapping("/spaceships")
    public String allSpaceShip (Model model){
        List<SpaceShip> spaceShips = spaceShipService.findAllSpaceShip();
        model.addAttribute("spaceships", spaceShips);

        return"spaceships";
    }

    @GetMapping("/active_spaceships")
    public String activeSpaceShips(Model model) {
        List<SpaceShip> spaceShips = spaceShipService.findActiveSpaceShip();
        model.addAttribute("spaceships", spaceShips);
        return "active_spaceships";
    }

    @GetMapping(value= {"/spaceship_crew/{registrationCode}"})
    public String spaceShipCrew( @PathVariable(name = "registrationCode") long registrationCode ,
                                 Model model){
        SpaceShip spaceShip = spaceShipService.findSpaceShip(registrationCode);
        model.addAttribute("reg", spaceShip.getName());
        model.addAttribute("crew", spaceShip.getCrews());
        return "spaceship_crew";
    }


}
